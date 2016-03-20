package com.learning.shilu.daggerdemo.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.configs.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HP on 3/14/2016.
 */
@Module
public class AndroidModule {
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
    public PreferencesModule getPreference(SharedPreferences sharedPreferences) {
        return PreferencesModule.getInstance(sharedPreferences);
    }

    @Provides
    @Singleton
    public Resources getResources(Context context) {
        return context.getResources();
    }

    /*@Provides
    @Singleton
    public StatusDataModule getStatusData(Resources resources) {
        return StatusDataModule.getInstance(resources);
    }*/
    /**
     * Helps save boolean value on the SharedPreferences
     *
     * @param key
     * @param bool
     * @return
     *//*
    @Provides
    @Named(Constants.KEY_STATUS)
    public Boolean putValue(String key, boolean bool) {
        return getSharedPreferences(getContext()).edit().putBoolean(key, bool).commit();
    }

    *//**
     * Helps save integer value on the SharedPreferences
     *
     * @param key
     * @param value
     * @return
     *//*
    @Provides
    @Named(Constants.KEY_STATUS)
    public Boolean putValue(String key, int value) {
        return getSharedPreferences(getContext()).edit().putInt(key, value).commit();
    }

    *//**
     * Helps save String value on the SharedPreferences
     *
     * @param key
     * @param text
     * @return
     *//*
    public Boolean putValue(String key, String text) {
        return getSharedPreferences(getContext()).edit().putString(key, text).commit();
    }*/

}
