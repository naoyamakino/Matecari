package com.naoya.matecari.di;

import com.naoya.matecari.ui.AllPagerFragment;
import com.naoya.matecari.ui.BaseActivity;
import com.naoya.matecari.ui.MainActivity;

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
                AllPagerFragment.class
        },
        addsTo = ApplicationModule.class
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
}