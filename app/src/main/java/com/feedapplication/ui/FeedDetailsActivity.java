package com.feedapplication.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.feedapplication.BaseActivity;
import com.feedapplication.R;
import com.feedapplication.constants.Constants;
import com.feedapplication.database.entity.FeedDetails;
import com.feedapplication.databinding.ActivityFeedDetailsBinding;
import com.feedapplication.utills.ConnectivityUtils;
import com.feedapplication.viewmodel.FeedDetailsViewModel;

public class FeedDetailsActivity extends BaseActivity {

    private FeedDetails mFeedDetails;
    private ActivityFeedDetailsBinding mBinding;
    private FeedDetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent()!=null){
            mFeedDetails = (FeedDetails) getIntent().getSerializableExtra(Constants.FEED_DETAILS_KEY);
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed_details);
        setSupportActionBar(mBinding.toolbar);
        mViewModel = ViewModelProviders.of(this).get(FeedDetailsViewModel.class);

        if (ConnectivityUtils.isNetworkEnabled(this)) {
            mViewModel.mIsOfflineMode.set(true);
        }else {
            mViewModel.mIsOfflineMode.set(false);
        }
        mBinding.setFeedDetailsViewModel(mViewModel);
        mBinding.setFeedDetails(mFeedDetails);
    }

    public void onLikeDislikeClick(View view){
        mFeedDetails.isLiked = !mFeedDetails.isLiked;
        mViewModel.setLikedDisLiked(this,mFeedDetails.id,mFeedDetails.isLiked);
        mBinding.setFeedDetails(mFeedDetails);
    }
}
