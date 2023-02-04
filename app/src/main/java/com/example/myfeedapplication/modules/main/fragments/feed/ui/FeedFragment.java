package com.example.myfeedapplication.modules.main.fragments.feed.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfeedapplication.application.FeedApplication;
import com.example.myfeedapplication.modules.main.fragments.feed.di.DaggerFeedComponent;
import com.example.myfeedapplication.modules.main.fragments.feed.di.FeedModule;
import com.example.myfeedapplication.modules.main.fragments.feed.mvp.FeedPresenter;
import com.example.myfeedapplication.modules.main.fragments.feed.mvp.FeedView;

import javax.inject.Inject;

public class FeedFragment extends Fragment {

    @Inject
    FeedPresenter feedPresenter;

    @Inject
    FeedView feedView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerFeedComponent.builder()
                .appComponent(FeedApplication.getInstance().appComponent())
                .feedModule(new FeedModule((AppCompatActivity)getActivity()))
                .build()
                .inject(this);

        feedPresenter.onCreateView();

       return feedView.getRootView();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        feedPresenter.onDestroy();
    }

}