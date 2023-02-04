package com.example.myfeedapplication.application;

import android.app.Application;
import android.content.Context;

import com.example.myfeedapplication.BuildConfig;
import com.example.myfeedapplication.application.di.AppComponent;
import com.example.myfeedapplication.application.di.DaggerAppComponent;
import com.example.myfeedapplication.application.di.modules.AppModules;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

public class FeedApplication extends Application {

    private static FeedApplication mInstance;

    private AppComponent appComponent;

    public static synchronized FeedApplication getInstance() {
        return mInstance;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        appComponent = DaggerAppComponent.builder().appModules(new AppModules(this)).build();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected void log(int priority, String tag, String m, Throwable t) {
                    super.log(priority, "TAG", m, t);
                }
            });
        }


    }


        public AppComponent appComponent() {
        return appComponent;
    }


}
