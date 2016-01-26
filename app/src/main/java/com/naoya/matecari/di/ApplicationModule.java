package com.naoya.matecari.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.naoya.matecari.App;
import com.naoya.matecari.data.Sources;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Naoya on 16-01-24.
 */

@Module(
        injects = App.class,
        library = true
)
public class ApplicationModule {
    private Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Singleton @ForApplication @Provides
    public Context provideContext() {
        return mContext;
    }

    @Singleton @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides @Singleton
    Sources provideSources(@ForApplication Context context, Gson gson) {
        return new Sources(context, gson);
    }
}
