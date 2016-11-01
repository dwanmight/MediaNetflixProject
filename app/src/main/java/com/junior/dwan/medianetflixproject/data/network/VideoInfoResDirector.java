package com.junior.dwan.medianetflixproject.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoInfoResDirector {

    @SerializedName("unit")
    @Expose
    public int unit;
    @SerializedName("show_id")
    @Expose
    public int showId;
    @SerializedName("show_title")
    @Expose
    public String showTitle;
    @SerializedName("release_year")
    @Expose
    public String releaseYear;
    @SerializedName("rating")
    @Expose
    public String rating;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("show_cast")
    @Expose
    public String showCast;
    @SerializedName("director")
    @Expose
    public String director;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("poster")
    @Expose
    public String poster;
    @SerializedName("mediatype")
    @Expose
    public int mediatype;
    @SerializedName("runtime")
    @Expose
    public String runtime;

    public String getShowTitle() {
        return showTitle;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getSummary() {
        return summary;
    }

    public String getPoster() {
        return poster;
    }

}
