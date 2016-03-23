package com.learning.shilu.daggerdemo.configs;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Shilu Shrestha on 3/18/2016.
 */
public class Status extends RealmObject {

    @PrimaryKey
    private String id;
    private String status;
    private int selectedPosition;
    private long date;
    private String dateVal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateVal() {
        return dateVal;
    }

    public void setDateVal(String dateVal) {
        this.dateVal = dateVal;
    }

}
