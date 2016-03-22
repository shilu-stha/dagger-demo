package com.learning.shilu.daggerdemo.module;

import android.content.res.Resources;

import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.Status;
import com.learning.shilu.daggerdemo.configs.Constants;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shilu Shrestha on 3/20/2016.
 */
@Module
public class StatusDataModule {

    private Resources resources;

    /*StatusDataModule(Resources resources) {
        this.resources = resources;
    }

    public static StatusDataModule getInstance(Resources resources) {
        return new StatusDataModule(resources);
    }
*/
    @Provides
    @Named(Constants.Inject.LIST_COLORS)
    public String[] provideColorsList(Resources resources) {
        return resources.getStringArray(R.array.color_list);
    }

    @Provides
    @Named(Constants.Inject.LIST_FEELS)
    public String[] provideFeelsList(Resources resources) {
        return resources.getStringArray(R.array.mood_list);
    }

    @Provides
    public ArrayList<Status> provideDummyData() {
        ArrayList<Status> statusArrayList = new ArrayList<>();
        statusArrayList.add(new Status("Discipline is just choosing between what you want now and what you want most.", 3, 1488499200000L));
        statusArrayList.add(new Status("A child of five would understand this. Send someone to fetch a child of five", 5, 1478044800000L));
        statusArrayList.add(new Status("You can do anything, but not everything.", 4, 1451606400000L));
        statusArrayList.add(new Status("We should be taught not to wait for inspiration to start a thing." +
                " Action always generates inspiration. Inspiration seldom generates action. ", 1, 1478822400000L));
        statusArrayList.add(new Status("You can do anything, but not everything.", 4, 1486771200000L));
        statusArrayList.add(new Status("I can accept failure, but I can't accept not trying.", 2, 1470614400000L));
        statusArrayList.add(new Status("There are many who dare not kill themselves for fear of what the neighbors will say. - Cyril Connolly", 5, 1441584000000L));
        statusArrayList.add(new Status("You can do anything, but not everything.", 4, 1467763200000L));
        return statusArrayList;
    }
}
