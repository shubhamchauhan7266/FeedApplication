package com.feedapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.PropertyChangeRegistry;
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
public class FeedDetailsViewModel extends ViewModel implements FeedDetailsRepo.IDatabaseListener {

    private String TAG = FeedDetailsViewModel.class.getSimpleName();
    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<FeedDetails>> mFeedDetailsList;

    public ObservableBoolean mIsItemAvailable = new ObservableBoolean(false);
    public ObservableBoolean mIsOfflineMode = new ObservableBoolean(false);

    //we will call this method to get the data
    public LiveData<List<FeedDetails>> getFeedDetailsList(BaseActivity context) {
        //if the list is null
        if (mFeedDetailsList == null) {
            mFeedDetailsList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
        }
        if (ConnectivityUtils.isNetworkEnabled(context)) {
            mIsOfflineMode.set(true);
            FeedDetailsRepo repo = new FeedDetailsRepo(context, context.getApplication(), this);
            repo.deleteAllRecords();
            loadFeedDetailsListData(context);
        } else {
            mIsOfflineMode.set(false);
            FeedDetailsRepo repo = new FeedDetailsRepo(context, context.getApplication(), this);
            ArrayList<FeedDetails> feedDetails = repo.getFeedDetailsList();
            mFeedDetailsList.setValue(getFilteredList(feedDetails));
        }

        //finally we will return the list
        return mFeedDetailsList;
    }

    public LiveData<List<FeedDetails>> getFeedDetailsListFromDB(BaseActivity context) {
        //if the list is null
        if (mFeedDetailsList == null) {
            mFeedDetailsList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
        }
        FeedDetailsRepo repo = new FeedDetailsRepo(context, context.getApplication(), this);
        ArrayList<FeedDetails> feedDetails = repo.getFeedDetailsList();
        mFeedDetailsList.setValue(getFilteredList(feedDetails));

        //finally we will return the list
        return mFeedDetailsList;
    }

    public void setLikedDisLiked(BaseActivity context, int id, boolean isLiked) {
        FeedDetailsRepo repo = new FeedDetailsRepo(context, context.getApplication(), this);
        repo.setLikedDisliked(id, isLiked);
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadFeedDetailsListData(final Context context) {

        Call<List<FeedDetails>> call = FeedApplication.getClient().getFeedDetails();

        call.enqueue(new Callback<List<FeedDetails>>() {
            @Override
            public void onResponse(Call<List<FeedDetails>> call,
                                   final Response<List<FeedDetails>> response) {
                assert response.body() != null;

                //finally we are setting the list to our MutableLiveData and DB
                FeedDetailsRepo repo = new FeedDetailsRepo(context, ((BaseActivity) context).getApplication(), FeedDetailsViewModel.this);
                repo.insertFeedDetailsList((ArrayList<FeedDetails>) response.body());
            }

            @Override
            public void onFailure(Call<List<FeedDetails>> call, Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
                ((BaseActivity) context).removeProgressDialog();
            }
        });
    }

    @Override
    public void onDbOperationSuccess(BaseActivity context) {
        FeedDetailsRepo repo = new FeedDetailsRepo(context, context.getApplication(), this);
        ArrayList<FeedDetails> feedDetailsList = repo.getFeedDetailsList();

        mFeedDetailsList.setValue(getFilteredList(feedDetailsList));
    }

    /**
     * Method is used to group data according to date.
     *
     * @param feedDetailsList feedDetailsList
     * @return a list of feed details
     */
    private ArrayList<FeedDetails> getFilteredList(ArrayList<FeedDetails> feedDetailsList) {

        //prepare data for grouping
        if (feedDetailsList != null && feedDetailsList.size() > 0) {

            long timeStamp = feedDetailsList.get(0).time;
            int id = feedDetailsList.get(0).id;
            for (FeedDetails feedDetails : feedDetailsList) {

                feedDetails.isShowDate = timeStamp != feedDetails.time || feedDetails.id == id;
                timeStamp = feedDetails.time;
            }
        }
        return feedDetailsList;
    }
}
