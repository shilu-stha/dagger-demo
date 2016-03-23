package com.learning.shilu.daggerdemo.module;

import android.content.res.Resources;

import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.configs.Status;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

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

    /*@Provides
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
    }*/

    @Provides
    @Named(Constants.Inject.DUMMY_ENTRY)
    public boolean provideDummyData(Realm realm) {
        if (realm.where(Status.class).findAll().size() == 0) {
            realm.beginTransaction();
            Status status = realm.createObject(Status.class);
            status.setDate(1488499200000L);
            status.setSelectedPosition(3);
            status.setStatus("Discipline is just choosing between what you want now and what you want most.");
            Status status2 = realm.createObject(Status.class);
            status2.setDate(1478044800000L);
            status2.setSelectedPosition(5);
            status2.setStatus("A child of five would understand this. Send someone to fetch a child of five");
            Status status3 = realm.createObject(Status.class);
            status3.setDate(1451606400000L);
            status3.setSelectedPosition(4);
            status3.setStatus("You can do anything, but not everything.");
            Status status4 = realm.createObject(Status.class);
            status4.setDate(1478822400000L);
            status4.setSelectedPosition(1);
            status4.setStatus("We should be taught not to wait for inspiration to start a thing." +
                    " Action always generates inspiration. Inspiration seldom generates action. ");
            Status status5 = realm.createObject(Status.class);
            status5.setDate(1470614400000L);
            status5.setSelectedPosition(2);
            status5.setStatus("I can accept failure, but I can't accept not trying.");
            Status status6 = realm.createObject(Status.class);
            status6.setDate(1441584000000L);
            status6.setSelectedPosition(5);
            status6.setStatus("There are many who dare not kill themselves for fear of what the neighbors will say. - Cyril Connolly");
            Status status7 = realm.createObject(Status.class);
            status7.setDate(1467763200000L);
            status7.setSelectedPosition(4);
            status7.setStatus("You can do anything, but not everything.");
            Status status8 = realm.createObject(Status.class);
            status8.setDate(1488499200000L);
            status8.setSelectedPosition(3);
            status8.setStatus("Discipline is just choosing between what you want now and what you want most.");
            realm.commitTransaction();
            return true;
        } else {
            return true;
        }
    }
}
