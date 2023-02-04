package com.example.myfeedapplication.application.di.modules;


import android.content.Context;

import com.example.myfeedapplication.application.di.AppScope;
import com.example.myfeedapplication.ext.CheckInternetConnection;
import com.example.myfeedapplication.ext.KeyboardHandler;
import com.example.myfeedapplication.ext.RequestPermissionHandler;
import com.example.myfeedapplication.ext.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModules {
    public  final Context context;

    public AppModules(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context context(){
        return  context;
    }

    @AppScope
    @Provides
    CheckInternetConnection checkInternetConnection(){
        return  new CheckInternetConnection(context);
    }

    @AppScope
    @Provides
    SchedulerProvider schedulerProvider(){
        return SchedulerProvider.getInstance();
    }

    @AppScope
    @Provides
    KeyboardHandler keyboardHandler(){
        return  new KeyboardHandler(context);
    }

    @AppScope
    @Provides
    RequestPermissionHandler requestPermissionHandler(){
        return  new RequestPermissionHandler();
    }

//    @AppScope
//    @Provides
//    CustomProgressDialogFragment  customProgressDialogFragment(){
//        return  new CustomProgressDialogFragment();
//    }


}

