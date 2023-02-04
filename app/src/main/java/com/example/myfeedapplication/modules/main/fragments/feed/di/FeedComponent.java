package com.example.myfeedapplication.modules.main.fragments.feed.di;


import com.example.myfeedapplication.application.di.AppComponent;
import com.example.myfeedapplication.modules.main.fragments.feed.ui.FeedFragment;

import dagger.Component;

@FeedScope
@Component(modules = {FeedModule.class},dependencies = AppComponent.class)
public interface FeedComponent {
    void inject(FeedFragment fragment);
}
