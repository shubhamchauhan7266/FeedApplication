package com.feedapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.feedapplication.BaseActivity;
import com.feedapplication.R;
import com.feedapplication.adapters.FeedDetailsListAdapter;
import com.feedapplication.database.entity.FeedDetails;
import com.feedapplication.databinding.ActivityHomeBinding;
import com.feedapplication.viewmodel.FeedDetailsViewModel;

import java.util.List;

public class HomeActivity extends BaseActivity implements FeedDetailsListAdapter.IFeedDetailsListAdapterCallBack, Observer<List<FeedDetails>> {

    private FeedDetailsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        showProgressDialog();
        mViewModel = ViewModelProviders.of(this).get(FeedDetailsViewModel.class);
        mViewModel.getFeedDetailsList(this).observe(this, this);
        binding.setFeedDetailsViewModel(mViewModel);
    }

    @Override
    public void onFeedDetailsClick(int position) {

    }

    @Override
    public void onChanged(@Nullable List<FeedDetails> feedDetails) {

        removeProgressDialog();

        if (feedDetails != null && feedDetails.size() > 0) {
//            mDailyWeatherDetailAdapter.setWeatherList(dailyWeatherForecast.list);
//            mDailyWeatherDetailAdapter.notifyDataSetChanged();
        }
    }
}
