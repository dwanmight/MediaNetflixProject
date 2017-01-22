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

/**
 * Created by Might on 19.09.2016.
 */
public class SearchWithDirectorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    SearchView mSearchView;
    DataManager mDataManager;
    ListView mListView;
    List<VideoInfoResDirector> list;
//    ArrayList<FilmAbout> mFilmAboutList;
    ArrayList<FilmAbout> listFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_with_director);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search_with_director);
        setTitle("Search with director");
        mSearchView = (SearchView) findViewById(R.id.director_searchView);
        mSearchView.setOnQueryTextListener(this);
        mListView = (ListView) findViewById(R.id.listview_director);
        listFilm=DataManager.getINSTANCE().getFilms();
        VideoAdapter adapter = new VideoAdapter(listFilm);
//        if(listFilm.size()==0)
            mListView.setAdapter(adapter);
//        setListAdapter(adapter);


        setSupportActionBar(toolbar);
        mDataManager = DataManager.getINSTANCE();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search_with_director);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_search_with_director);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private class VideoAdapter extends ArrayAdapter<FilmAbout> {
        public VideoAdapter(List<FilmAbout> lists) {
            super(SearchWithDirectorActivity.this, R.layout.item_list_with_director, lists);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.item_list_with_director, parent, false);
            }
            FilmAbout fa=getItem(position);
            TextView title = (TextView) v.findViewById(R.id.item_title);
            title.setText(fa.getTitle());
            TextView director = (TextView) v.findViewById(R.id.item_director);
            director.setText(fa.getDirector());
            ImageView poster=(ImageView)v.findViewById(R.id.list_poster_imgView);
//            poster.setImageURI(Uri.parse(fa.getPoster()));
            Picasso.with(SearchWithDirectorActivity.this).
                    load(fa.getPoster()).
                    into(poster);
            System.out.println(fa.getPoster());
            return v;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search_with_director);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.saved_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        switch (item.getItemId()) {

            case R.id.drawer_menu_saved_movies:
                intent = new Intent(this, SavedMoviesActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.drawer_menu_search_with_title:
                intent = new Intent(this, SearchWithTitleActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.drawer_menu_search_with_director:

                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search_with_director);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        getFilmWithDirector(query);
        return true;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

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

    @Override
    protected void onStop() {
        super.onStop();
        DataManager.getINSTANCE().clearList();
    }
}
