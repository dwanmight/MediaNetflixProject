package com.junior.dwan.medianetflixproject.data.managers;

import android.media.Image;
import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by Might on 19.09.2016.
 */
public class FilmAbout {
    private UUID mUUID;
    private String mTitle;
    private String mDirector;
    private String mPoster;

    public FilmAbout(){
        mUUID=UUID.randomUUID();
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }

    public int getYearRelease() {
        return mYearRelease;
    }

    public void setYearRelease(int yearRelease) {
        mYearRelease = yearRelease;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    private float mRating;
    private int mYearRelease;
    private String mSummary;


}
