package com.learning.shilu.daggerdemo;

import android.content.Context;

/**
 * Created by HP on 3/11/2016.
 */
public class DaggerDemoSettings {

    /**
     * Provides application context to be used in the entire application
     *
     * @return
     */
    public Context getContext() {
        return DaggerDemoApplication.getInstance().getApplicationContext();
    }

    /**
     * Provides SharedPreferences to be used through out the application
     *
     * @param context
     * @return
     *//*
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("dagger demo", context.MODE_PRIVATE);
    }

    *//**
     * Helps save boolean value on the SharedPreferences
     *
     * @param key
     * @param bool
     * @return
     *//*
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
