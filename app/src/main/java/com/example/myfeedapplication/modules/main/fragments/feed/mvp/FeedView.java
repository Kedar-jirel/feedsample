package com.example.myfeedapplication.modules.main.fragments.feed.mvp;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfeedapplication.R;
import com.example.myfeedapplication.application.models.remote.Feed;
import com.example.myfeedapplication.modules.main.fragments.feed.ui.FeedAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ViewConstructor")
public class FeedView extends FrameLayout {

    public final AppCompatActivity activity;

    @BindView(R.id.rv_feed)
    public  RecyclerView mFeedRecyclerView;


    @BindView(R.id.shimmer_view_container)
    public ShimmerFrameLayout mShimmerView;


    FeedAdapter feedAdapter;

    public FeedView(AppCompatActivity activity) {
        super(activity);
        this.activity = activity;
        inflate(activity, R.layout.fragment_feed, this);
        ButterKnife.bind(this);
        initAdapter();
    }



    private void initAdapter() {
        feedAdapter = new FeedAdapter(activity);
        mFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mFeedRecyclerView.setNestedScrollingEnabled(true);
        mFeedRecyclerView.setAdapter(feedAdapter);
    }

    public void onUpdateAdapter(List<Feed> feeds) {
        feedAdapter.onUpdateFeeds(feeds);
    }
}


