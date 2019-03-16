package com.feedapplication.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feedapplication.R;
import com.feedapplication.database.entity.FeedDetails;
import com.feedapplication.databinding.FeedDetailsItemBinding;

import java.util.ArrayList;


/**
 * This adapter class is used for showing feed details list.
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
        FeedDetailsItemBinding feedDetailsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.feed_details_item, parent, false);
        return new ViewHolder(feedDetailsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.setFeedDetails(mFeedDetailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mFeedDetailsList != null ? mFeedDetailsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final FeedDetailsItemBinding binding;
        public ViewHolder(@NonNull FeedDetailsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setFeedDetails(FeedDetails feedDetails){
            binding.setFeedDetails(feedDetails);
        }
    }

    public interface IFeedDetailsListAdapterCallBack{
        void onFeedDetailsClick(int position);
    }
}
