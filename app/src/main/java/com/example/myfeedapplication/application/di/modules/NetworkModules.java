package com.example.myfeedapplication.application.di.modules;

import android.content.Context;

import com.example.myfeedapplication.application.di.UnsafeOkHttpClient;
import com.example.myfeedapplication.application.di.AppNetwork;
import com.example.myfeedapplication.application.di.AppScope;
import com.example.myfeedapplication.application.di.common.Constants;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModules {

    @AppScope
    @Provides
    public Cache cache(Context context) {
        return new Cache(new File(context.getCacheDir(), Constants.HTTP_DIR_CACHE), Constants.CACHE_SIZE);
    }


    @AppScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }


    @AppScope
    @Provides
    OkHttpClient provideClient(Cache cache, Context context) {
        return  UnsafeOkHttpClient.getUnsafeOkHttpClient(cache, context);
    }

    @AppScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @AppScope
    @Provides
    public AppNetwork appNetwork(Retrofit retrofit) {
        return retrofit.create(AppNetwork.class);
    }

}

