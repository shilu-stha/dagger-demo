package com.learning.shilu.daggerdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shilu Shrestha on 3/18/2016.
 */
public class Status implements Parcelable{

    String status;
    int selectedMood;

    public Status(String status, int selectedMood) {
        this.status = status;
        this.selectedMood = selectedMood;
    }

    protected Status(Parcel in) {
        status = in.readString();
        selectedMood = in.readInt();
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
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSelectedMood() {
        return selectedMood;
    }

    public void setSelectedMood(int selectedMood) {
        this.selectedMood = selectedMood;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeInt(selectedMood);
    }
}
