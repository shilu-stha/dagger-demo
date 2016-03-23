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

    public Status(String status, int selectedPosition, long date) {
        this.status = status;
        this.selectedPosition = selectedPosition;
        this.date = date;
        this.dateVal = Config.getDate(date);
    }

    /*protected Status(Parcel in) {
        status = in.readString();
        selectedPosition = in.readInt();
        date = in.readLong();
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };*/

    public Status() {

    }

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

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeInt(selectedPosition);
        dest.writeLong(date);
    }*/
}