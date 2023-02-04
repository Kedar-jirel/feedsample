package com.example.myfeedapplication.application.models.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.myfeedapplication.application.models.remote.Feed;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DbSetup {

    public static final String DB_NAME = "feed.db";
    public static final int DB_VERSION = 1;

    public DbSetup() {
    }


    public static abstract class FeedTable {
        /*Table Name of  MasterTable*/
        public final static String TABLE_FEEDS = "TABLE_FEEDS";

        /*column name of Master table*/
        public final static String ID = "ID";
        public final static String CONTENT = "CONTENT";

        static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_FEEDS + " ( " +
                        ID + " VARCHAR(255)  PRIMARY KEY, " +
                        CONTENT + " TEXT " + " ) ";

        static ContentValues toContentValues(Feed data) {
            ContentValues values = new ContentValues();
            values.put(ID,data.id);
            values.put(CONTENT, new Gson().toJson(data));
            return values;
        }

        static ArrayList<Feed> parseCursor(Cursor cursor) {
            ArrayList<Feed> dataArrayList = new ArrayList<>();
            if (cursor.getCount() >= 1) {
                while (cursor.moveToNext()) {
                    dataArrayList.add(new Gson().fromJson(cursor.getString(cursor.getColumnIndexOrThrow(CONTENT)), Feed.class));
                }
            }
            return dataArrayList;
        }
    }


    public static class DB_TAG {
        public final static String TAG_UPDATE = "UPDATE";
        public final static String TAG_INSERT = "INSERT";
        public final static String TAG_DELETE = "DELETE";
    }

}

