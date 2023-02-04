package com.example.myfeedapplication.modules.main.fragments.feed.mvp;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfeedapplication.application.models.remote.Feed;
import com.example.myfeedapplication.ext.CheckInternetConnection;
import com.example.myfeedapplication.ext.SchedulerProvider;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;


public class FeedPresenter {

    final  AppCompatActivity activity;
    final SchedulerProvider schedulerProvider;
    final FeedInteractor feedInteractor;
    final FeedView feedView;
    final  CheckInternetConnection checkInternetConnection;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FeedPresenter(AppCompatActivity activity, SchedulerProvider schedulerProvider, FeedInteractor feedInteractor, FeedView feedView, CheckInternetConnection checkInternetConnection) {

        this.activity = activity;
        this.schedulerProvider = schedulerProvider;
        this.feedInteractor = feedInteractor;
        this.feedView = feedView;
        this.checkInternetConnection = checkInternetConnection;
    }

    public void onCreateView() {
        feedView.mShimmerView.setVisibility(View.VISIBLE);
        compositeDisposable.add(
                feedInteractor.onGetFeeds()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(this::onFeedFetchSuccess, this::showErrorResponse));
    }

    private void showErrorResponse(Throwable throwable) {
        feedView.mShimmerView.setVisibility(View.GONE);
    }

    private void onFeedFetchSuccess(ArrayList<Feed> feeds) {
        if(checkInternetConnection.isNetworkConnected()){
            saveFeeds(feeds);
        }

        feedView.mShimmerView.setVisibility(View.GONE);
        feedView.onUpdateAdapter(feeds);
    }

    private void saveFeeds(ArrayList<Feed> feeds) {
        compositeDisposable.add(feedInteractor.save(feeds)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(this::saveSuccess,this::onError));
    }

    private void onError(Throwable throwable) {
    }

    private void saveSuccess(Boolean aBoolean) {
        if(aBoolean){
            Log.e("Feed","saved");
        }else{
            Log.e("Feed","Failed");
        }
    }

    public void onDestroy() {
        try {
            if(!compositeDisposable.isDisposed()){
                compositeDisposable.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
