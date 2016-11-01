package com.junior.dwan.medianetflixproject.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.junior.dwan.medianetflixproject.R;
import com.junior.dwan.medianetflixproject.data.managers.DataManager;
import com.junior.dwan.medianetflixproject.data.managers.FilmAbout;
import com.junior.dwan.medianetflixproject.data.network.VideoInfoRes;
import com.junior.dwan.medianetflixproject.data.network.VideoInfoResDirector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.list;

/**
 * Created by Might on 19.09.2016.
 */


    private void getFilmWithDirector(String director) {
        Call<List<VideoInfoResDirector>> call=mDataManager.getVideoWithDirector(director);
//        Call<List<VideoInfoResDirector>> call = mDataManager.getVideoWithDirectorQuen();
        list = new ArrayList<VideoInfoResDirector>();
        call.enqueue(new Callback<List<VideoInfoResDirector>>() {

            @Override
            public void onResponse(Call<List<VideoInfoResDirector>> call, Response<List<VideoInfoResDirector>> response) {
                if (response.code() == 200) {
                    mListView.setVisibility(View.VISIBLE);
                    mSearchView.setVisibility(View.GONE);
                    System.out.println("200 CODE");
                    for (int i = 0; i < response.body().size(); i++) {
                        list.add(response.body().get(i));
                        FilmAbout fa=new FilmAbout();
                        fa.setTitle(list.get(i).getShowTitle());
                        fa.setDirector(list.get(i).getDirector());
                        fa.setPoster(response.body().get(i).getPoster());
                        System.out.println("image ="+response.body().get(i).getPoster());
                        listFilm.add(fa);
                        Log.i("TAG","film "+listFilm.get(i).getTitle());
                        Log.i("TAG"," list"+list.get(i).getShowTitle());


                    }


                    for (VideoInfoResDirector s : list) {
                        System.out.println(s.getShowTitle());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<VideoInfoResDirector>> call, Throwable t) {

            }
        });
    }


