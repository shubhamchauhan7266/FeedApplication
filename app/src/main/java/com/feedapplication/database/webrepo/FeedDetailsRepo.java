package com.feedapplication.database.webrepo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.feedapplication.database.FeedDetailsDatabase;
import com.feedapplication.database.dao.FeedDetailsDao;
import com.feedapplication.database.entity.FeedDetails;

import java.util.ArrayList;

/**
 * This Repository class is used to handle database operation.
 *
 * @author Shubham Chauhan
 */
public class FeedDetailsRepo {

    private final Context mContext;
    private final FeedDetailsDao mFeedDetailsDao;

    public FeedDetailsRepo(Context context, Application application) {
        mContext = context;
        FeedDetailsDatabase database = FeedDetailsDatabase.getInstance(application);
        mFeedDetailsDao = database.getFeedDetailsDao();
    }

    public void insertFeedDetailsList(ArrayList<FeedDetails> feedDetailsList) {
        insertFeedDetailsTask(feedDetailsList);
    }

    public ArrayList<FeedDetails> getFeedDetailsList() {
        return (ArrayList<FeedDetails>) mFeedDetailsDao.getFeedDetailsList();
    }

    public FeedDetails getFeedDetails(int feedId) {
        return mFeedDetailsDao.getFeedDetails(feedId);
    }

    @SuppressLint("StaticFieldLeak")
    private void insertFeedDetailsTask(final ArrayList<FeedDetails> feedDetailsList) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mFeedDetailsDao.insert(feedDetailsList);
                return null;
            }
        }.execute();
    }
}
