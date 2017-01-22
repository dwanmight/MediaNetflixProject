package com.junior.dwan.medianetflixproject.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Might on 19.09.2016.
 */
public class MediaNetflixApplication extends Application{
    public static SharedPreferences sSharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();

        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }
}
