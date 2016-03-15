package com.learning.shilu.daggerdemo.configs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shilu Shrestha on 3/14/2016.
 */
public class PrefConfig {

    private static PrefConfig prefConfig;
    private final SharedPreferences sharedPreferences;

    public static synchronized PrefConfig getInstance(Context context) {
        if (prefConfig == null)
            prefConfig = new PrefConfig(context);
        return prefConfig;
    }

    private PrefConfig(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
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
        editor.putString(Constants.KEY_TODAY_STATUS, status);
        editor.commit();
    }

    /**
     *
     */
    public void setStatus(Boolean status) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(Constants.KEY_STATUS, status);
        editor.commit();
    }

    /**
     */
    public String getTodayStatus(String defaultPath) {
        return sharedPreferences.getString(Constants.KEY_TODAY_STATUS, defaultPath);
    }

    /**
     */
    public Boolean getStatus() {
        return sharedPreferences.getBoolean(Constants.KEY_STATUS, true);
    }

    public void setSelectedPosition(int mPosition) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(Constants.KEY_SELECTED_POSITION, mPosition);
        editor.commit();
    }

    public int getSelectedPosition() {
        return sharedPreferences.getInt(Constants.KEY_SELECTED_POSITION, 0);
    }
}
