package com.learning.shilu.daggerdemo.module;

import android.content.SharedPreferences;

import com.learning.shilu.daggerdemo.configs.Constants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shilu Shrestha on 3/15/2016.
 */
@Module
public class PreferencesModule {

    SharedPreferences sharedPref;

    public PreferencesModule(SharedPreferences sharedPreferences) {
        this.sharedPref = sharedPreferences;
    }

    public PreferencesModule() {

    }

    public static PreferencesModule getInstance(SharedPreferences sharedPreferences) {
        return new PreferencesModule(sharedPreferences);
    }

    /**
     *
     */
    public void setTodayStatus(String status) {
        sharedPref
                .edit()
                .putString(Constants.KEY_TODAY_STATUS, status)
                .commit();
    }

    /**
     *
     */
    public void setStatus(Boolean status) {
        sharedPref
                .edit()
                .putBoolean(Constants.KEY_STATUS, status)
                .commit();
    }

    public void setSelectedPosition(int mPosition) {
        sharedPref.edit()
                .putInt(Constants.KEY_SELECTED_POSITION, mPosition)
                .commit();
    }

    /**
     */
    @Provides
    public String getTodayStatus() {
        return sharedPref.getString(Constants.KEY_TODAY_STATUS, "");
    }

    /**
     */
    @Provides
    @Named(Constants.KEY_STATUS)
    public Boolean getStatus() {
        return sharedPref.getBoolean(Constants.KEY_STATUS, true);
    }


    @Provides
    public int getSelectedPosition(SharedPreferences sharedPref) {
        return sharedPref.getInt(Constants.KEY_SELECTED_POSITION, 0);
    }

    public int getSelectedPos() {
        return sharedPref.getInt(Constants.KEY_SELECTED_POSITION, 0);
    }
}
