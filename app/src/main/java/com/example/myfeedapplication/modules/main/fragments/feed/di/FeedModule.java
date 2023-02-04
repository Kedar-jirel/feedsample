package com.example.myfeedapplication.modules.main.fragments.feed.di;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myfeedapplication.application.di.AppNetwork;
import com.example.myfeedapplication.application.models.local.DbManager;
import com.example.myfeedapplication.ext.CheckInternetConnection;
import com.example.myfeedapplication.ext.SchedulerProvider;
import com.example.myfeedapplication.modules.main.fragments.feed.mvp.FeedInteractor;
import com.example.myfeedapplication.modules.main.fragments.feed.mvp.FeedPresenter;
import com.example.myfeedapplication.modules.main.fragments.feed.mvp.FeedView;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedModule {

    public final AppCompatActivity activity;

    public FeedModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @FeedScope
    @Provides
    FeedView feedView(){
        return new FeedView(activity);
    }

    @FeedScope
    @Provides
    FeedInteractor feedInteraction(AppNetwork appNetwork, DbManager dbManager, CheckInternetConnection checkInternetConnection){
        return  new FeedInteractor(appNetwork,activity, dbManager,checkInternetConnection);
    }

    @FeedScope
    @Provides
    FeedPresenter feedPresenter(SchedulerProvider schedulerProvider, FeedInteractor homeInteractor, FeedView homeView, CheckInternetConnection checkInternetConnection){
        return  new FeedPresenter(activity,schedulerProvider,homeInteractor,homeView,checkInternetConnection);
    }

}
