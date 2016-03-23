package com.learning.shilu.daggerdemo.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.learning.shilu.daggerdemo.DaggerDemoApplication;
import com.learning.shilu.daggerdemo.configs.Config;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.configs.PrefConfig;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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
    public Context provideContext() {
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
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREF_NAME, context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public PrefConfig providePreference(SharedPreferences sharedPreferences) {
        return PrefConfig.getInstance(sharedPreferences);
    }

    @Provides
    @Singleton
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    public Realm provideRealm(Context context) {
        //Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        //Get a Realm instance for this thread
        return Realm.getInstance(realmConfig);
    }

    @Provides
    @Named(Constants.Inject.TODAYS_DATE)
    public String provideTodaysDate() {
        return Config.getDate(System.currentTimeMillis());
    }
}
