package com.junior.dwan.medianetflixproject.ui.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junior.dwan.medianetflixproject.R;
import com.junior.dwan.medianetflixproject.data.managers.DataManager;
import com.junior.dwan.medianetflixproject.utils.ConstantManager;
import com.squareup.picasso.Picasso;

/**
 * Created by Might on 20.09.2016.
 */
public class DetailsMovieActivity extends AppCompatActivity {
    DataManager mDataManager;
    LinearLayout mLinearLayout;
    ImageView mPoster;
    TextView mRelease,mRating,mDirector,mSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mLinearLayout=(LinearLayout)findViewById(R.id.details_lin_lay);
        mDataManager=DataManager.getINSTANCE();
        initializeDetailsView();
        getVideoInfo();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(getIntent().getStringExtra(ConstantManager.EXTRA_TITLE));
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



    }

    private void initializeDetailsView() {
        mPoster=(ImageView)findViewById(R.id.details_poster_imageView);
        mRelease=(TextView)findViewById(R.id.details_release_tv);
        mRating=(TextView)findViewById(R.id.details_rating_tv);
        mDirector=(TextView)findViewById(R.id.details_director_tv);
        mSummary=(TextView)findViewById(R.id.details_summary_tv);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_save:
                // Not implemented here
                return false;
            case android.R.id.home:
                if (NavUtils.getParentActivityName(this)!=null){
                    NavUtils.navigateUpFromSameTask(this);
                }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    private void getVideoInfo(){


        Picasso.with(this)
                .load(getIntent().getStringExtra(ConstantManager.EXTRA_POSTER))
                .into(mPoster);
        mRelease.setText(getString(R.string.details_release) + getIntent().getStringExtra(ConstantManager.EXTRA_RELEASE_YEAR));
        mRating.setText(getString(R.string.details_rating)+getIntent().getStringExtra(ConstantManager.EXTRA_RATING));
        mDirector.setText(getString(R.string.details_director)+getIntent().getStringExtra(ConstantManager.EXTRA_DIRECTOR));
        mSummary.setText(getString(R.string.details_summary) + getIntent().getStringExtra(ConstantManager.EXTRA_SUMMARY));


    }

}
