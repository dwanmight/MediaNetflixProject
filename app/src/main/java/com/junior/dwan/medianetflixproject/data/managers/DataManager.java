package com.junior.dwan.medianetflixproject.data.managers;

import com.junior.dwan.medianetflixproject.data.network.RestService;
import com.junior.dwan.medianetflixproject.data.network.ServiceGenerator;
import com.junior.dwan.medianetflixproject.data.network.VideoInfoRes;
import com.junior.dwan.medianetflixproject.data.network.VideoInfoResDirector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;

public class DataManager {
    private static DataManager INSTANCE = null;

    private PreferencesManager mPreferencesManager;
    private RestService mRestService;
    ArrayList<FilmAbout> mFilms;

    public DataManager() {
        this.mPreferencesManager = new PreferencesManager();
        this.mRestService = ServiceGenerator.createService(RestService.class);
        mFilms=new ArrayList<FilmAbout>();
//        for (int i = 0; i < 10; i++) {
//            FilmAbout fa=new FilmAbout();
//            fa.setDirector("Director="+i);
//            fa.setTitle("Title="+i);
//            mFilms.add(fa);
//        }
    }

    public static DataManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

    //region=======Network=======

    public Call<VideoInfoRes> getCurrentVideoInfo(String title){
        return mRestService.getCurrentVideoInfo(title);
    }

    public Call<List<VideoInfoResDirector>> getVideoWithDirector(String director){
        return mRestService.getVideoWithDirector(director);
    }

    public Call<List<VideoInfoResDirector>> getVideoWithDirectorQuen(){
        return mRestService.getVideoWithDirectorQuentin();
    }

    public ArrayList<FilmAbout> getFilms(){
        return mFilms;
    }

    public FilmAbout getFilm(UUID id){
        for (FilmAbout fa:mFilms){
            if(fa.getUUID().equals(id)){
                return fa;
            }
        }
        return null;
    }

    public ArrayList<FilmAbout> clearList(){
        mFilms.clear();
        return mFilms;
    }

    //endregion

    //region=======DataBase=======

    //endregion
}
