package com.junior.dwan.medianetflixproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.junior.dwan.medianetflixproject.R;
import com.junior.dwan.medianetflixproject.data.managers.DataManager;
import com.junior.dwan.medianetflixproject.data.network.VideoInfoRes;
import com.junior.dwan.medianetflixproject.utils.ConstantManager;
import com.junior.dwan.medianetflixproject.utils.StatusNetworkChecker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Might on 19.09.2016.
 */
public class SearchWithTitleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener {
    DataManager mDataManager;
    Toolbar mToolbar;
    CoordinatorLayout mCoordinatorLayout;
    ImageView mPoster;
    TextView mRelease, mRating, mDirector, mSummary;
    SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Search with title");
        setContentView(R.layout.activity_search_with_title);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_search_with_title);
        mSearchView=(SearchView)findViewById(R.id.title_searchView);
        mSearchView.setOnQueryTextListener(this);
        initializeDetailsView();
        setupToolBar(mToolbar);


        mDataManager = DataManager.getINSTANCE();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search_with_title);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_search_with_title);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void initializeDetailsView() {
        mPoster = (ImageView) findViewById(R.id.details_poster_imageView);
        mRelease = (TextView) findViewById(R.id.details_release_tv);
        mRating = (TextView) findViewById(R.id.details_rating_tv);
        mDirector = (TextView) findViewById(R.id.details_director_tv);
        mSummary = (TextView) findViewById(R.id.details_summary_tv);
    }

    private void setupToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Find movie");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search_with_title);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

                break;
            case R.id.drawer_menu_search_with_director:
                intent = new Intent(this, SearchWithDirectorActivity.class);
                startActivity(intent);
                finish();
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search_with_title);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getFilmWithTitle(String title) {
        if (StatusNetworkChecker.IsNetworkAvailable(this)) {
            Call<VideoInfoRes> call = mDataManager.getCurrentVideoInfo(title);
            call.enqueue(new Callback<VideoInfoRes>() {
                @Override
                public void onResponse(Call<VideoInfoRes> call, Response<VideoInfoRes> response) {
                    if (response.code() == 200) {

                        getVideoInfo(response);
                        System.out.println(response.raw() + " " + response.body().getSummary());
                        System.out.println(response.body().getPoster());
                        System.out.println(response.body().getDirector());
                    } else if (response.code() == 404) {
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        mSearchView.clearFocus();
                        Snackbar.make(mCoordinatorLayout, "We could not find anything with this title, try another one", Snackbar.LENGTH_LONG).show();
                    } else System.out.println("Error" + response.errorBody() + " " + response.raw());

                }

                @Override
                public void onFailure(Call<VideoInfoRes> call, Throwable t) {

                }
            });
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            mSearchView.clearFocus();
            Snackbar.make(mCoordinatorLayout,"Network is unavailable",Snackbar.LENGTH_LONG)
                    .setAction("Turn on network", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openApplicationSetting();

                        }
                    }).show();
        }
    }

    private void openApplicationSetting() {
        Intent appSettingIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivityForResult(appSettingIntent, ConstantManager.PERMISSION_REQUEST_SETTING_CODE);
    }

    private void getVideoInfo(Response<VideoInfoRes> response) {
        Intent intent=new Intent(this, DetailsMovieActivity.class);
        intent.putExtra(ConstantManager.EXTRA_POSTER,response.body().getPoster());
        intent.putExtra(ConstantManager.EXTRA_RELEASE_YEAR,response.body().getReleaseYear());
        intent.putExtra(ConstantManager.EXTRA_RATING, response.body().getRating());
        intent.putExtra(ConstantManager.EXTRA_DIRECTOR,response.body().getDirector());
        intent.putExtra(ConstantManager.EXTRA_SUMMARY,response.body().getSummary());
        intent.putExtra(ConstantManager.EXTRA_TITLE,response.body().getShowTitle());

        startActivity(intent);
//        Picasso.with(this)
//                .load(response.body().getPoster())
//                .into(mPoster);
//        mRelease.setText(getString(R.string.details_release) + response.body().getReleaseYear());
//        mRating.setText(getString(R.string.details_rating) + response.body().getRating());
//        mDirector.setText(getString(R.string.details_director) + response.body().getDirector());
//        mSummary.setText(getString(R.string.details_summary) + response.body().getSummary());

    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        getFilmWithTitle(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
