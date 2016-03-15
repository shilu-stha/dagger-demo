package com.learning.shilu.daggerdemo;

import com.learning.shilu.daggerdemo.activities.MainActivity;
import com.learning.shilu.daggerdemo.fragments.FirstFragment;
import com.learning.shilu.daggerdemo.fragments.SecondFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HP on 3/15/2016.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        PreferencesModule.class
})
public interface DemoComponent {
    void inject(MainActivity target);

    void inject(FirstFragment target);

    void inject(SecondFragment target);
}
