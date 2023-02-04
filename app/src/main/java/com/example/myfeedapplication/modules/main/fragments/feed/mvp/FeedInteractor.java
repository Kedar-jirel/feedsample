package com.example.myfeedapplication.modules.main.fragments.feed.mvp;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfeedapplication.application.di.AppNetwork;
import com.example.myfeedapplication.application.models.local.DbManager;
import com.example.myfeedapplication.application.models.local.DbSetup;
import com.example.myfeedapplication.application.models.local.TableInfo;
import com.example.myfeedapplication.application.models.remote.Feed;
import com.example.myfeedapplication.ext.CheckInternetConnection;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public class FeedInteractor {

    final AppNetwork appNetwork;
    final  AppCompatActivity activity;
    final  DbManager dbManager;
    final CheckInternetConnection checkInternetConnection;

    public FeedInteractor(AppNetwork appNetwork, AppCompatActivity activity, DbManager dbManager, CheckInternetConnection checkInternetConnection) {
        this.appNetwork = appNetwork;
        this.activity = activity;
        this.dbManager = dbManager;
        this.checkInternetConnection = checkInternetConnection;
    }

    public Observable<ArrayList<Feed>> onGetFeeds() {
        if(checkInternetConnection.isNetworkConnected()){
            return appNetwork.getFeeds();
        }else {
           return  dbManager.getLocalFeedsList();
        }
    }


    public Observable<Boolean> save(ArrayList<Feed> data) {
        return dbManager.insetIntoTable(new TableInfo(DbSetup.FeedTable.TABLE_FEEDS, data, DbSetup.DB_TAG.TAG_INSERT));
    }




}
