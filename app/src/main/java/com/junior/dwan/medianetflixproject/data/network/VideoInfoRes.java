package com.junior.dwan.medianetflixproject.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Might on 19.09.2016.
 */
public class VideoInfoRes {

    @SerializedName("unit")
    @Expose
    private int unit;
    @SerializedName("show_id")
    @Expose
    private int showId;
    @SerializedName("show_title")
    @Expose
    private String showTitle;
    @SerializedName("release_year")
    @Expose
    private String releaseYear;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("show_cast")
    @Expose
    private String showCast;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("mediatype")
    @Expose
    private int mediatype;
    @SerializedName("runtime")
    @Expose
    private String runtime;

    public String getPoster() {
        return poster;
    }

    public String getSummary() {
        return summary;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public int getShowId() {
        return showId;
    }

    public int getUnit() {
        return unit;
    }
}
