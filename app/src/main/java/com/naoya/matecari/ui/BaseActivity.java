package com.naoya.matecari.ui;

import com.naoya.matecari.App;
import com.naoya.matecari.di.ActivityModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Naoya on 16-01-24.
 */
public class BaseActivity extends AppCompatActivity {
    private ObjectGraph mObjectGraph;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App app = (App) getApplicationContext();
        mObjectGraph = app.getObjectGraph().plus(getModules());
    }

    /**
     * A list of modules to use for the individual activity graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected List<Object> getModules() {
        return Collections.singletonList((Object) new ActivityModule(this));
    }

    /** Inject the supplied {@code object} using the activity-specific graph. */
    public void inject(Object module) {
        mObjectGraph.inject(module);
    }
}
