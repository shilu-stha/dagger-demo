package com.learning.shilu.daggerdemo.configs;

import android.content.Context;

/**
 * Created by HP on 3/11/2016.
 */
public class DaggerDemoSettings {

    private static DaggerDemoSettings daggerSettings;
    private final Context context;

    public DaggerDemoSettings(Context context) {
        this.context = context;
    }


    public static DaggerDemoSettings getInstance(Context context) {
        if (daggerSettings == null) {
            synchronized (DaggerDemoSettings.class) {
                daggerSettings = new DaggerDemoSettings(context);
            }
        }
        return daggerSettings;
    }


    public void setStatus(Boolean status) {
        PrefConfig.getInstance(context).setStatus(status);
    }

    public void setTodayStatus(String status) {
        PrefConfig.getInstance(context).setTodayStatus(status);
    }

    public Boolean getStatus() {
        return PrefConfig.getInstance(context).getStatus();
    }

    public String getTodayStatus() {
        return PrefConfig.getInstance(context).getTodayStatus("");
    }
}
