package com.example.myfeedapplication.application.models.local;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myfeedapplication.application.models.local.DbSetup;
import com.example.myfeedapplication.application.models.remote.Feed;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.sqlbrite2.BriteDatabase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;


public class DbManager {
    private BriteDatabase db;
    private Gson gson;

    @Inject
    public DbManager(BriteDatabase db, Gson gson) {
        this.db = db;
        this.gson = gson;
    }

    public Observable<Boolean> insetIntoTable(final TableInfo tableInfo) {
        // Seems using fromCallable is safer than creating an observable directly.
        // If this one doesnot work, use defer.... i think this one will solve a lot of problems.
        return Observable.fromCallable(() -> {
                    long result = 0;
                    BriteDatabase.Transaction transaction = db.newTransaction();
            try {
                switch (tableInfo.getmTableName()) {
                    case DbSetup.FeedTable.TABLE_FEEDS:
                        switch (tableInfo.getTag()) {
                                    case DbSetup.DB_TAG.TAG_INSERT:
                                        final ArrayList<Feed> feedArrayList = gson.fromJson(
                                                gson.toJson(tableInfo.getRows()),
                                                new TypeToken<ArrayList<Feed>>() {
                                                }.getType());
                                        for (Feed o : feedArrayList) {
                                            result = db.insert(
                                                    DbSetup.FeedTable.TABLE_FEEDS,
                                                    DbSetup.FeedTable.toContentValues(o), SQLiteDatabase.CONFLICT_REPLACE
                                            );
                                        }
                                        break;
                                    default:
                                        final ArrayList<Feed> updateFeedList = gson.fromJson(
                                                gson.toJson(tableInfo.getRows()),
                                                new TypeToken<ArrayList<Feed>>() {
                                                }.getType());

                                        break;
                                }
                               break;
                            default:
                                break;
                        }
                        transaction.markSuccessful();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        transaction.end();
                    }

                    // Return Result
                    return result > 0;
                });
    }

    private long updateFeeds(Object rows) {
        ContentValues updateContentValues = new ContentValues();
        updateContentValues.put(DbSetup.FeedTable.CONTENT, new Gson().toJson(rows));
        return db.update(DbSetup.FeedTable.TABLE_FEEDS, updateContentValues, DbSetup.FeedTable.ID, "1");
    }

    public Observable<ArrayList<Feed>> getLocalFeedsList() {
        return db.createQuery(DbSetup.FeedTable.TABLE_FEEDS, "SELECT * FROM " + DbSetup.FeedTable.TABLE_FEEDS
        ).map(query -> DbSetup.FeedTable.parseCursor(query.run()));

    }

    public Observable<ArrayList<Feed>> getFeedById(String id) {
        return db.createQuery(DbSetup.FeedTable.TABLE_FEEDS, "SELECT * FROM " + DbSetup.FeedTable.TABLE_FEEDS + " WHERE " +
                DbSetup.FeedTable.ID + " = " + id).map(query -> DbSetup.FeedTable.parseCursor(query.run()));

    }


}

