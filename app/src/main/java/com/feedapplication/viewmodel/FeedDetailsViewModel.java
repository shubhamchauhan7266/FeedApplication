package com.feedapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.Bindable;
import android.util.Log;

import com.feedapplication.BaseActivity;
import com.feedapplication.FeedApplication;
import com.feedapplication.database.entity.FeedDetails;
import com.feedapplication.database.webrepo.FeedDetailsRepo;
import com.feedapplication.utills.ConnectivityUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * View Model class Feed details.
 *
 * @author Shubham Chauhan
 */
public class FeedDetailsViewModel extends ViewModel {

    private String TAG = FeedDetailsViewModel.class.getSimpleName();

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<FeedDetails>> mFeedDetailsList;

    public MutableLiveData<Boolean> mIsItemAvailable;

    //we will call this method to get the data
    public LiveData<List<FeedDetails>> getFeedDetailsList(BaseActivity context) {
        //if the list is null
        if (mFeedDetailsList == null) {
            mFeedDetailsList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
        }
        if (ConnectivityUtils.isNetworkEnabled(context)) {
            loadFeedDetailsListData(context);
        } else {
            FeedDetailsRepo repo = new FeedDetailsRepo(context, context.getApplication());
            ArrayList<FeedDetails> feedDetails = repo.getFeedDetailsList();
            mFeedDetailsList.setValue(feedDetails);
        }

        //finally we will return the list
        return mFeedDetailsList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadFeedDetailsListData(final Context context) {

        Call<List<FeedDetails>> call = FeedApplication.getClient().getFeedDetails();

        call.enqueue(new Callback<List<FeedDetails>>() {
            @Override
            public void onResponse(Call<List<FeedDetails>> call,
                                   Response<List<FeedDetails>> response) {
                assert response.body() != null;

                //finally we are setting the list to our MutableLiveData and DB
                FeedDetailsRepo repo = new FeedDetailsRepo(context, ((BaseActivity) context).getApplication());
                repo.insertFeedDetailsList((ArrayList<FeedDetails>) response.body());
                mFeedDetailsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<FeedDetails>> call, Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }
        });
    }
}
