package com.example.myfeedapplication.application.di.modules;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.myfeedapplication.application.di.AppScope;
import com.example.myfeedapplication.application.models.local.DbManager;
import com.example.myfeedapplication.application.models.local.DbOpenHelper;
import com.example.myfeedapplication.ext.SchedulerProvider;
import com.google.gson.Gson;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import dagger.Module;
import dagger.Provides;


@Module
public class DbModule {
    @Provides
    @NonNull
    @AppScope
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }

    @Provides
    @NonNull
    @AppScope
    public BriteDatabase provideSqlBrite(SQLiteOpenHelper sqLiteOpenHelper, SchedulerProvider schedulerProvider) {
        return new SqlBrite.Builder()
                .build().wrapDatabaseHelper(sqLiteOpenHelper, schedulerProvider.io());
    }

    @Provides
    @NonNull
    @AppScope
    public DbManager provideDbManager(BriteDatabase briteDatabase, Gson gson) {
        return new DbManager(briteDatabase, gson);
    }

}














