package com.example.myfeedapplication.application.di;

import android.content.Context;

import com.example.myfeedapplication.application.di.modules.AppModules;
import com.example.myfeedapplication.application.di.modules.GsonModule;
import com.example.myfeedapplication.application.di.modules.NetworkModules;
import com.example.myfeedapplication.ext.KeyboardHandler;
import com.example.myfeedapplication.ext.RequestPermissionHandler;
import com.example.myfeedapplication.ext.SchedulerProvider;

import dagger.Component;
import okhttp3.OkHttpClient;

@AppScope
@Component(modules = {AppModules.class, NetworkModules.class, GsonModule.class})
public interface AppComponent {
    Context context();

    OkHttpClient okHttpClient();

    AppNetwork appNetwork();

    SchedulerProvider schedulerProvider();

    RequestPermissionHandler requestPermissionHandler();

    KeyboardHandler keyboardHandler();

}
