package com.naoya.matecari.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by Naoya on 16-01-24.
 */
public class ApplicationModule {
    private Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Singleton @ForApplication @Provides public Context provideContext() {
        return mContext;
    }
}
