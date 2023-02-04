package com.example.myfeedapplication.modules.main.fragments.feed.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
;
import com.bumptech.glide.request.RequestOptions;
import com.example.myfeedapplication.R;
import com.example.myfeedapplication.application.models.remote.Feed;
import com.example.myfeedapplication.databinding.ItemRowFeedBinding;
import com.example.myfeedapplication.ext.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyFeedViewHolder> {

    final AppCompatActivity activity;
    ArrayList<Feed>  feeds = new ArrayList<>();

    public FeedAdapter(AppCompatActivity activity) {
        this.activity = activity;
    }


    @NonNull
    @Override
    public MyFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyFeedViewHolder myFeedViewHolder = new MyFeedViewHolder(ItemRowFeedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        return myFeedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFeedViewHolder holder, int position) {
        final Feed  feed = feeds.get(position);
        holder.binding.tvFullName.setText("Post by "+feed.creator.firstName+" "+ feed.creator.lastName);

        holder.binding.tvDate.setText(DateTimeUtils.convertStringToDesiredDateFormat(feed.createdAt,"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","dd MMM YYY, hh:mm:ss a"));
        holder.binding.tvDesc.setText(feed.postText);

        Glide.with(activity).load(feed.creator.avatar).apply(new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)).into(holder.binding.ivAvatar);

        SliderAdapter sliderAdapter = new SliderAdapter(activity,feed.getImages());
        holder.binding.rvSlider.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));
        holder.binding.rvSlider.setNestedScrollingEnabled(false);
        holder.binding.rvSlider.setAdapter(sliderAdapter);

    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public void onUpdateFeeds(List<Feed> feeds) {
        if(!feeds.isEmpty()){
            this.feeds.addAll(feeds);
            notifyDataSetChanged();
        }
    }


    public class MyFeedViewHolder extends  RecyclerView.ViewHolder {

        final  ItemRowFeedBinding binding;

        public MyFeedViewHolder(@NonNull ItemRowFeedBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
