package com.feedapplication.network;

import com.feedapplication.constants.ApiConstants;
import com.feedapplication.database.entity.FeedDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This interface is used for call Api.
 *
 * @author Shubham Chauhan
 */
public interface ApiClientInterface {

    @GET(ApiConstants.FEED_DETAILS)
    Call<List<FeedDetails>> getFeedDetails();
}
