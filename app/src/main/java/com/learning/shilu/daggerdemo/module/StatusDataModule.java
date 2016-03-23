package com.learning.shilu.daggerdemo.module;

import android.content.res.Resources;

import com.learning.shilu.daggerdemo.R;
import com.learning.shilu.daggerdemo.configs.Config;
import com.learning.shilu.daggerdemo.configs.Constants;
import com.learning.shilu.daggerdemo.configs.Status;

import java.util.UUID;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Shilu Shrestha on 3/20/2016.
 */
@Module
public class StatusDataModule {

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
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Status status = realm.createObject(Status.class);
                    status.setId(UUID.randomUUID().toString());
                    status.setDate(1488499200000L);
                    status.setSelectedPosition(3);
                    status.setStatus("Discipline is just choosing between what you want now and what you want most.");
                    status.setDateVal(Config.getDate(status.getDate()));
                    Status status2 = realm.createObject(Status.class);
                    status2.setId(UUID.randomUUID().toString());
                    status2.setDate(1478044800000L);
                    status2.setSelectedPosition(5);
                    status2.setStatus("A child of five would understand this. Send someone to fetch a child of five");
                    status2.setDateVal(Config.getDate(status2.getDate()));
                    Status status3 = realm.createObject(Status.class);
                    status3.setId(UUID.randomUUID().toString());
                    status3.setDate(1451606400000L);
                    status3.setSelectedPosition(4);
                    status3.setStatus("You can do anything, but not everything.");
                    status3.setDateVal(Config.getDate(status3.getDate()));
                    Status status4 = realm.createObject(Status.class);
                    status4.setId(UUID.randomUUID().toString());
                    status4.setDate(1478822400000L);
                    status4.setSelectedPosition(1);
                    status4.setStatus("We should be taught not to wait for inspiration to start a thing." +
                            " Action always generates inspiration. Inspiration seldom generates action. ");
                    status4.setDateVal(Config.getDate(status4.getDate()));
                    Status status5 = realm.createObject(Status.class);
                    status5.setId(UUID.randomUUID().toString());
                    status5.setDate(1470614400000L);
                    status5.setSelectedPosition(2);
                    status5.setStatus("I can accept failure, but I can't accept not trying.");
                    status5.setDateVal(Config.getDate(status5.getDate()));
                    Status status6 = realm.createObject(Status.class);
                    status6.setId(UUID.randomUUID().toString());
                    status6.setDate(1441584000000L);
                    status6.setSelectedPosition(5);
                    status6.setStatus("There are many who dare not kill themselves for fear of what the neighbors will say. - Cyril Connolly");
                    status6.setDateVal(Config.getDate(status6.getDate()));
                    Status status7 = realm.createObject(Status.class);
                    status7.setId(UUID.randomUUID().toString());
                    status7.setDate(1467763200000L);
                    status7.setSelectedPosition(4);
                    status7.setStatus("You can do anything, but not everything.");
                    status7.setDateVal(Config.getDate(status7.getDate()));
                    Status status8 = realm.createObject(Status.class);
                    status8.setId(UUID.randomUUID().toString());
                    status8.setDate(1488499200000L);
                    status8.setSelectedPosition(3);
                    status8.setStatus("Discipline is just choosing between what you want now and what you want most.");
                    status8.setDateVal(Config.getDate(status8.getDate()));
                }
            });
        }
        return true;
    }
}
