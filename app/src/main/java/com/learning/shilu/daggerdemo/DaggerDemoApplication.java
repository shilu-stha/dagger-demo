package com.learning.shilu.daggerdemo;

import android.app.Application;

import com.learning.shilu.daggerdemo.module.ApplicationModule;
import com.learning.shilu.daggerdemo.module.StatusDataModule;

/**
 * Created by Shilu Shrestha on 3/11/2016.
 */
public class DaggerDemoApplication extends Application {
    private static DemoComponent component;
    private static DaggerDemoApplication instance;

    // static Context context;
    // private static DaggerDemoSettings daggerSettings;

    @Override
    public void onCreate() {
        super.onCreate();
        // context = this;
        // daggerSettings = DaggerDemoSettings.getInstance(context);

        instance = this;

        // Perform injection
        //component = DaggerDemoComponent.builder()
        //        .androidModule(new ApplicationModule())
        //        .statusDataModule(new StatusDataModule())
        //.build();
        component = DaggerComponentInitializer.init();

    }

//    public static Context getContext() {
//        return context;
//    }

//    public static DaggerDemoSettings getDaggerSettings() {
//        return daggerSettings;
//    }

    public static DaggerDemoApplication getInstance() {
        return instance;
    }

    public static DemoComponent getComponent() {
        return component;
    }

    public final static class DaggerComponentInitializer {

        public static DemoComponent init() {
            return DaggerDemoComponent.builder()
                    .applicationModule(new ApplicationModule())
                    .statusDataModule(new StatusDataModule())
                    .build();
        }
    }
}
