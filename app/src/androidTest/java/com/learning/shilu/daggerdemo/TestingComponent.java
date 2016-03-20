package com.learning.shilu.daggerdemo;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shilu Shrestha on 3/20/2016.
 */
@Singleton
@Component(modules = {MockApplicationModule.class})
public class TestingComponent {
}
