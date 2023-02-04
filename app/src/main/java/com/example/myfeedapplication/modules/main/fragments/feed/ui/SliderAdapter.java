package com.example.myfeedapplication.modules.main.fragments.feed.ui;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myfeedapplication.R;
import com.example.myfeedapplication.databinding.SliderLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderAdapterViewHolder> {

    private final List<String> mSliderItems;
    private final AppCompatActivity activity;

    public SliderAdapter(AppCompatActivity activity, ArrayList<String> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SliderAdapterViewHolder viewHolder = new SliderAdapterViewHolder(SliderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder holder, final int position) {

        try {
            Glide.with(activity).load(mSliderItems.get(position)).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    holder.binding.loading.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.binding.loading.setVisibility(View.GONE);
                    return false;
                }
            }).apply(new RequestOptions().error(R.drawable.placeholder)).into(holder.binding.ivImage);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mSliderItems.size();
    }

    class SliderAdapterViewHolder extends RecyclerView.ViewHolder {

        SliderLayoutBinding binding;

        public SliderAdapterViewHolder(SliderLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}