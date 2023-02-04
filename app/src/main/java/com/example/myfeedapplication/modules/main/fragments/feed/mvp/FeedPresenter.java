package com.example.myfeedapplication.modules.main.fragments.feed.mvp;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfeedapplication.application.models.remote.Feed;
import com.example.myfeedapplication.ext.SchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class FeedPresenter {

    final  AppCompatActivity activity;
    final SchedulerProvider schedulerProvider;
    final FeedInteractor feedInteractor;
    final FeedView feedView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FeedPresenter(AppCompatActivity activity, SchedulerProvider schedulerProvider, FeedInteractor feedInteractor, FeedView feedView) {

        this.activity = activity;
        this.schedulerProvider = schedulerProvider;
        this.feedInteractor = feedInteractor;
        this.feedView = feedView;
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

    private void onFeedFetchSuccess(List<Feed> feeds) {
        feedView.mShimmerView.setVisibility(View.GONE);
        feedView.onUpdateAdapter(feeds);
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
