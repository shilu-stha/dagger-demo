package com.learning.shilu.daggerdemo.configs;

import android.content.Context;
import android.content.SharedPreferences;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;

/**
 * Created by HP on 3/14/2016.
 */
public class PrefConfig {

    private static final String PREF_NAME = "DaggerDemoPreferences";
    private static final String KEY_TODAY_STATUS = "TodayStatus";
    private static final String KEY_STATUS = "NewStatus";
    private static PrefConfig prefConfig;
    private final SharedPreferences sharedPreferences;

    public static synchronized PrefConfig getInstance() {
        if (prefConfig == null)
            prefConfig = new PrefConfig(DaggerDemoApplication.getContext());
        return prefConfig;
    }

    private PrefConfig(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Return {@link SharedPreferences} editor so as to edit the preference value
     *
     * @return {@link SharedPreferences.Editor}
     */
    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    /**
     *
     */
    public void setTodayStatus(String status) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(KEY_TODAY_STATUS, status);
        editor.commit();
    }

    /**
     *
     */
    public void setStatus(Boolean status) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(KEY_STATUS, status);
        editor.commit();
    }

    /**
     */
    public String getTodayStatus(String defaultPath) {
        return sharedPreferences.getString(KEY_TODAY_STATUS, defaultPath);
    }

    /**
     */
    public Boolean getStatus() {
        return sharedPreferences.getBoolean(KEY_STATUS, true);
    }

//    /*
//     * Helps save boolean value on the SharedPreferences
//     *
//     * @param key
//     * @param bool
//     * @return
//     */
//    public Boolean putValue(String key, boolean bool) {
//        return getSharedPreferences(getContext()).edit().putBoolean(key, bool).commit();
//    }
//
//    /*
//     * Helps save integer value on the SharedPreferences
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Boolean putValue(String key, int value) {
//        return getSharedPreferences(getContext()).edit().putInt(key, value).commit();
//    }
//
//    /*
//     * Helps save String value on the SharedPreferences
//     *
//     * @param key
//     * @param text
//     * @return
//     */
//    public Boolean putValue(String key, String text) {
//        return getSharedPreferences(getContext()).edit().putString(key, text).commit();
//    }
}
