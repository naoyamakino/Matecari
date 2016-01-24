package com.naoya.matecari;

import com.naoya.matecari.di.ApplicationModule;

import android.os.StrictMode;

import java.util.Collections;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Naoya on 16-01-24.
 */
public class App extends android.app.Application{
    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(getModules().toArray());

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectCustomSlowCalls()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .penaltyDeath()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .detectLeakedSqlLiteObjects()
                .detectLeakedRegistrationObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    protected List<Object> getModules() {
        return Collections.singletonList((Object) new ApplicationModule(this));
    }

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }
}
