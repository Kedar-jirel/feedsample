package com.example.myfeedapplication.application.di;

import com.example.myfeedapplication.application.models.remote.Feed;

import java.util.List;

import dagger.Module;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface AppNetwork {

    @GET("web-n-app-tasks/posts")
    Observable<List<Feed>> getFeeds();


}
