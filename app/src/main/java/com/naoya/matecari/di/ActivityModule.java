package com.naoya.matecari.di;

import com.google.gson.Gson;

import com.naoya.matecari.data.Sources;
import com.naoya.matecari.ui.PagerFragment;
import com.naoya.matecari.ui.BaseActivity;
import com.naoya.matecari.ui.MainActivity;
import com.squareup.picasso.Picasso;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Naoya on 16-01-24.
 */

@Module(
        injects = {
                MainActivity.class,
                PagerFragment.class
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class ActivityModule {
    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        mActivity = activity;
    }

    @Provides @Singleton @ForActivity
    Context provideContext() {
        return mActivity;
    }

    @Provides @Singleton
    Picasso providePicasso (@ForActivity Context context) {
        return Picasso.with(context);
    }

    @Provides @Singleton
    Sources provideSources(@ForActivity Context context, Gson gson) {
        return new Sources(context, gson);
    }
}
