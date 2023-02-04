package com.example.myfeedapplication.modules.main.fragments.feed.di;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myfeedapplication.application.di.AppNetwork;
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
    FeedView homeView(){
        return new FeedView(activity);
    }

    @FeedScope
    @Provides
    FeedInteractor homeInteractor(AppNetwork appNetwork){
        return  new FeedInteractor(appNetwork,activity);
    }

    @FeedScope
    @Provides
    FeedPresenter homePresenter(SchedulerProvider schedulerProvider, FeedInteractor homeInteractor, FeedView homeView){
        return  new FeedPresenter(activity,schedulerProvider,homeInteractor,homeView);
    }

}
