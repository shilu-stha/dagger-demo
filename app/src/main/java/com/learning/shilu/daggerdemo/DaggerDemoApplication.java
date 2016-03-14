package com.learning.shilu.daggerdemo;

import android.app.Application;
import android.content.Context;

import com.learning.shilu.daggerdemo.configs.DaggerDemoSettings;

/**
 * Created by Shilu Shrestha on 3/11/2016.
 */
public class DaggerDemoApplication extends Application {
    static Context context;
    static Application application;
    private static DaggerDemoSettings daggerSettings;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        application = this;
        daggerSettings = DaggerDemoSettings.getInstance(context);
    }

    public static Context getContext() {
        return context;
    }

    public static DaggerDemoSettings getDaggerSettings() {
        return daggerSettings;
    }
}
