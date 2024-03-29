package com.feedapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.feedapplication.BaseActivity;
import com.feedapplication.R;
import com.feedapplication.adapters.FeedDetailsListAdapter;
import com.feedapplication.constants.Constants;
import com.feedapplication.database.entity.FeedDetails;
import com.feedapplication.databinding.ActivityHomeBinding;
import com.feedapplication.viewmodel.FeedDetailsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Activity class is used to show Feed details list.
 *
 * @author Shubham Chauhan
 */
public class HomeActivity extends BaseActivity implements FeedDetailsListAdapter.IFeedDetailsListAdapterCallBack, Observer<List<FeedDetails>> {

    private FeedDetailsViewModel mViewModel;
    private ActivityHomeBinding mBinding;
    private boolean mIsRefreshRequired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(mBinding.toolbar);
        showProgressDialog();
        mViewModel = ViewModelProviders.of(this).get(FeedDetailsViewModel.class);
        mViewModel.getFeedDetailsList(this).observe(this, this);
        mBinding.setFeedDetailsViewModel(mViewModel);
    }

    @Override
    public void onFeedDetailsClick(int position) {

        Intent intent = new Intent(this, FeedDetailsActivity.class);
        intent.putExtra(Constants.FEED_DETAILS_KEY, ((FeedDetailsListAdapter) Objects.requireNonNull(mBinding.rvFeedList.getAdapter())).getFeedDetailsList().get(position));
        startActivity(intent);
    }

    @Override
    public void onLikeDislikeClick(int position) {
        FeedDetails feedDetails = ((FeedDetailsListAdapter) Objects.requireNonNull(mBinding.rvFeedList.getAdapter())).getFeedDetailsList().get(position);
        feedDetails.isLiked = !feedDetails.isLiked;
        mViewModel.setLikedDisLiked(this, feedDetails.id, feedDetails.isLiked);
        Objects.requireNonNull(mBinding.rvFeedList.getAdapter()).notifyItemChanged(position);
    }

    @Override
    public void onChanged(@Nullable List<FeedDetails> feedDetailsList) {

        removeProgressDialog();

        if (feedDetailsList != null && feedDetailsList.size() > 0) {
            mViewModel.mIsItemAvailable.set(true);
            FeedDetailsListAdapter feedDetailsListAdapter = (FeedDetailsListAdapter) mBinding.rvFeedList.getAdapter();

            if (feedDetailsListAdapter != null) {
                feedDetailsListAdapter.setFeedDetailsList((ArrayList<FeedDetails>) feedDetailsList);
                feedDetailsListAdapter.notifyDataSetChanged();
            } else {
                mBinding.rvFeedList.setLayoutManager(new LinearLayoutManager(this));
                mBinding.rvFeedList.setAdapter(new FeedDetailsListAdapter(this, (ArrayList<FeedDetails>) feedDetailsList));
            }
        } else {
            mViewModel.mIsItemAvailable.set(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mIsRefreshRequired){
            mIsRefreshRequired = false;
            mViewModel.getFeedDetailsListFromDB(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mIsRefreshRequired = true;
    }
}
