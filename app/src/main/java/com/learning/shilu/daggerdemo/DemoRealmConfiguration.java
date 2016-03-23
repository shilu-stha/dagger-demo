package com.learning.shilu.daggerdemo;

import android.content.Context;

import io.realm.RealmConfiguration;

/**
 * Created by Shilu Shrestha on 3/23/2016.
 */
public class DemoRealmConfiguration {
    private static final long SCHEMA_VERSION = 1;
    private static final String DATABASE_NAME = "daggerdemo.realm";

    public RealmConfiguration startConfiguration(Context context) {
        return new RealmConfiguration.Builder(context)
                .name(DATABASE_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .setModules(new StatusModule())
                .build();
    }

    public void migration() {
        // update for migration
    }
}
