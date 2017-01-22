package com.junior.dwan.medianetflixproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.NetworkInterface;

/**
 * Created by Might on 22.09.2016.
 */
public class StatusNetworkChecker {

    public static boolean IsNetworkAvailable(Context context){
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }

//    protected boolean isOnline() {
//        String cs = Context.CONNECTIVITY_SERVICE;
//        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(cs);
//        if (cm.getActiveNetworkInfo() == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
