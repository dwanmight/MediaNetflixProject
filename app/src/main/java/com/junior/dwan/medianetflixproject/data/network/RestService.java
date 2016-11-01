package com.junior.dwan.medianetflixproject.data.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Might on 19.09.2016.
 */
public interface RestService {
    @GET("api.php")
    Call<VideoInfoRes> getCurrentVideoInfo(@Query("title") String showFilm);

    @GET("api.php")
    Call<List<VideoInfoResDirector>> getVideoWithDirector(@Query("director") String director);

}
