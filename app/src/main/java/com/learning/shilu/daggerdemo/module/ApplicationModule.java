package com.learning.shilu.daggerdemo.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.configs.PrefConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shilu Shrestha on 3/14/2016.
 */
@Module
public class ApplicationModule {
    /**
     * Provides application context to be used in the entire application
     *
     * @return
     */
    @Provides
    @Singleton
    public Context getContext() {
        return DaggerDemoApplication.getInstance().getApplicationContext();
    }


    /**
     * Provides SharedPreferences to be used through out the application
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREF_NAME, context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public PrefConfig getPreference(SharedPreferences sharedPreferences) {
        return PrefConfig.getInstance(sharedPreferences);
    }

    @Provides
    @Singleton
    public Resources getResources(Context context) {
        return context.getResources();
    }

}
