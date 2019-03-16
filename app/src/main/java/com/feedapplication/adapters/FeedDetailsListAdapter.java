package com.feedapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feedapplication.R;
import com.feedapplication.database.entity.FeedDetails;

import java.util.ArrayList;


/**
 * This adapter class is used for City list which come when we search list.
 *
 * @author Shubham Chauhan
 */
public class FeedDetailsListAdapter extends RecyclerView.Adapter<FeedDetailsListAdapter.ViewHolder> {

    private ArrayList<FeedDetails> mFeedDetailsList;
    private IFeedDetailsListAdapterCallBack mIFeedDetailsListAdapterCallBack;

    public FeedDetailsListAdapter(Context context, ArrayList<FeedDetails> feedDetailsList) {
        mIFeedDetailsListAdapterCallBack = (IFeedDetailsListAdapterCallBack) context;
        mFeedDetailsList = feedDetailsList;
    }

    /**
     * Method is used to set feed details list
     *
     * @param feedDetailsList a list of feed
     */
    public void setFeedDetailsList(ArrayList<FeedDetails> feedDetailsList) {
        mFeedDetailsList = feedDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_details_item, parent, false);

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

//        viewHolder.tvCityName.setText(mCityList.get(position).name);
//
//        viewHolder.tvCityName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mICitySearchListCallBack.onCityClick(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mFeedDetailsList != null ? mFeedDetailsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            tvCityName = itemView.findViewById(R.id.tv_city_name);
        }
    }

    public interface IFeedDetailsListAdapterCallBack{
        void onFeedDetailsClick(int position);
    }
}
