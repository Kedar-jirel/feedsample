package com.example.myfeedapplication.modules.main.fragments.feed.mvp;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfeedapplication.application.di.AppNetwork;
import com.example.myfeedapplication.application.models.remote.Feed;

import java.util.List;

import io.reactivex.Observable;


public class FeedInteractor {

    final AppNetwork appNetwork;
    final  AppCompatActivity activity;

    public FeedInteractor(AppNetwork appNetwork, AppCompatActivity activity) {
        this.appNetwork = appNetwork;
        this.activity = activity;
    }

    public Observable<List<Feed>> onGetFeeds() {
        return appNetwork.getFeeds();
    }
}
