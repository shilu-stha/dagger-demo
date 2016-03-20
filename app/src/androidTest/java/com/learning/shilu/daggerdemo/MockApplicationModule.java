package com.learning.shilu.daggerdemo;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shilu Shrestha on 3/20/2016.
 */
@Module
public class MockApplicationModule {
    @Provides
    @Singleton
    public Context getContext() {
        return DaggerDemoApplication.getInstance().getApplicationContext();
    }

//    @Provides
//    @Singleton
//    public SharedPreferences getSharedPreferences(Context context) {
//        return Mockito.mock(context.getSharedPreferences(Constants.PREF_NAME, context.MODE_PRIVATE));
//    }

}
