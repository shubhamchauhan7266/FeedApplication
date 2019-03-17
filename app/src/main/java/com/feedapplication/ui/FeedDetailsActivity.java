package com.feedapplication.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.feedapplication.BaseActivity;
import com.feedapplication.R;
import com.feedapplication.constants.Constants;
import com.feedapplication.database.entity.FeedDetails;
import com.feedapplication.databinding.ActivityFeedDetailsBinding;

public class FeedDetailsActivity extends BaseActivity {

    private FeedDetails mFeedDetails;
    private ActivityFeedDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent()!=null){
            mFeedDetails = (FeedDetails) getIntent().getSerializableExtra(Constants.FEED_DETAILS_KEY);
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed_details);
        setSupportActionBar(mBinding.toolbar);
        mBinding.setFeedDetails(mFeedDetails);
    }
}
