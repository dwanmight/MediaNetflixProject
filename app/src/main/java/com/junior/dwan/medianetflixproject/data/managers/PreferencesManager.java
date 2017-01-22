package com.junior.dwan.medianetflixproject.data.managers;

import android.content.SharedPreferences;

import com.junior.dwan.medianetflixproject.utils.MediaNetflixApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Might on 19.09.2016.
 */
public class PreferencesManager {
    private SharedPreferences mSharedPreferences;
//    private static final String [] USER_FIELDS={ConstantManager.USER_PHONE_KEY,ConstantManager.USER_MAIL_KEY,ConstantManager.USER_VK_KEY,ConstantManager.USER_GIT_KEY,ConstantManager.USER_BIO_KEY};

    public PreferencesManager() {
        this.mSharedPreferences= MediaNetflixApplication.getSharedPreferences();
    }

    public void saveUserProfileData(List<String> userFields){
//        SharedPreferences.Editor editor=mSharedPreferences.edit();
//        for(int i =0;i<USER_FIELDS.length;i++){
//            editor.putString(USER_FIELDS[i],userFields.get(i));
//        }
//        editor.apply();
    }

    public List<String> loadUserProfileData(){
        List<String> userFields =new ArrayList<>();
//        userFields.add(mSharedPreferences.getString(ConstantManager.USER_PHONE_KEY,"null"));
//        userFields.add(mSharedPreferences.getString(ConstantManager.USER_MAIL_KEY,"null"));
//        userFields.add(mSharedPreferences.getString(ConstantManager.USER_VK_KEY,"null"));
//        userFields.add(mSharedPreferences.getString(ConstantManager.USER_GIT_KEY,"null"));
//        userFields.add(mSharedPreferences.getString(ConstantManager.USER_BIO_KEY, "null"));
//
        return userFields;
    }
}
