package com.learning.shilu.daggerdemo;

import com.learning.shilu.daggerdemo.activities.MainActivity;
import com.learning.shilu.daggerdemo.activities.StatusDetailActivity;
import com.learning.shilu.daggerdemo.fragments.FirstFragment;
import com.learning.shilu.daggerdemo.fragments.SecondFragment;
import com.learning.shilu.daggerdemo.module.ApplicationModule;
import com.learning.shilu.daggerdemo.module.PreferencesModule;
import com.learning.shilu.daggerdemo.module.StatusDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shilu Shrestha on 3/15/2016.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        PreferencesModule.class,
        StatusDataModule.class
})
public interface DemoComponent {
    void inject(MainActivity target);

    void inject(StatusDetailActivity target);

    void inject(FirstFragment target);

    void inject(SecondFragment target);
}
