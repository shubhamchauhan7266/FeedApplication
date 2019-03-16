package com.feedapplication.ui;

import android.os.Bundle;

import com.feedapplication.BaseActivity;
import com.feedapplication.R;
import com.feedapplication.constants.Constants;
import com.feedapplication.database.entity.FeedDetails;

import java.io.Serializable;

public class FeedDetailsActivity extends BaseActivity {

    private FeedDetails mFeedDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);

        if(getIntent()!=null){
            mFeedDetails = (FeedDetails) getIntent().getSerializableExtra(Constants.FEED_DETAILS_KEY);
        }
    }
}
