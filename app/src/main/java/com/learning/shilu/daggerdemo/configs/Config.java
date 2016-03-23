package com.learning.shilu.daggerdemo.configs;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shilu Shrestha on 3/22/2016.
 */
public class Config {

    public static String getDate(long date1) {
        Date date = new Date(date1);
        SimpleDateFormat df2 = new SimpleDateFormat(Constants.DATE_FORMAT);
        return df2.format(date);
    }
}
