package com.feedapplication;

import android.support.multidex.MultiDexApplication;

import com.feedapplication.constants.ApiConstants;
import com.feedapplication.constants.Constants;
import com.feedapplication.network.ApiClientInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This Application class is used by whole application.
 *
 * @author Shubham Chauhan
 */
public class FeedApplication extends MultiDexApplication {

    private static Retrofit mRetrofitClient;

    @Override
    public void onCreate() {
        super.onCreate();

        // Init Retrofit CLIENT
        setRetrofitApiClient();
    }

    /**
     * Setup Retrofit CLIENT
     */
    private void setRetrofitApiClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get Retrofit API client
     *
     * @return ApiClientInterface
     */
    public static ApiClientInterface getClient() {
        return mRetrofitClient.create(ApiClientInterface.class);
    }
}
