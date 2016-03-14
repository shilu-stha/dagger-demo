package com.learning.shilu.daggerdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by HP on 3/11/2016.
 */
public class DaggerDemoApplication extends Application {
    static Context context;
    static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        application = this;
    }

    public static Application getInstance() {
        return application;
    }

    public static Context getContext() {
        return context;
    }
}
