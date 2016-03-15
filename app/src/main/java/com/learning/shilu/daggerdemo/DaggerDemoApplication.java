package com.learning.shilu.daggerdemo;

import android.app.Application;
import android.content.Context;

import com.learning.shilu.daggerdemo.configs.DaggerDemoSettings;

/**
 * Created by Shilu Shrestha on 3/11/2016.
 */
public abstract class DaggerDemoApplication extends Application {
    static Context context;
    private static DaggerDemoSettings daggerSettings;
    private static DaggerDemoApplication instance;
    private static DemoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
//        context = this;
//        daggerSettings = DaggerDemoSettings.getInstance(context);

        instance = this;

        // Perform injection
        component = DaggerComponentInitializer.init();

    }

//    public static Context getContext() {
//        return context;
//    }

    public static DaggerDemoSettings getDaggerSettings() {
        return daggerSettings;
    }

    public static DaggerDemoApplication getInstance() {
        return instance;
    }

    public static DemoComponent component() {
        return component;
    }

    public final static class DaggerComponentInitializer {

        public static DemoComponent init() {
            return DaggerDemoComponent.builder()
                    .androidModule(new AndroidModule())
                    .preferencesModule(new PreferencesModule())
                    .build();
        }

    }
}
