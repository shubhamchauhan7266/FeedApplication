package com.feedapplication.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.feedapplication.R;
import com.feedapplication.utills.OtherUtils;
import com.google.gson.annotations.Expose;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * This Entity class is used to create a table for feed Details.
 *
 * @author Shubham Chauhan
 */
@Entity(tableName = "feed_details")
public class FeedDetails implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @Expose
    public int id;

    @Expose
    public String name;

    @Expose
    public String imageUrl;

    @Expose
    public String title;

    @Expose
    public String text;

    @Expose
    public long time;

    @Expose
    public String description;

    @Expose
    public boolean isLiked;

    @Ignore
    public boolean isTextOnly() {
        if (!OtherUtils.isNullOrEmpty(text) && !OtherUtils.isNullOrEmpty(imageUrl)) {
            return false;
        } else if (OtherUtils.isNullOrEmpty(text)) {
            return false;
        } else {
            return true;
        }
    }

    @Ignore
    public boolean isPostOnly() {
        if (!OtherUtils.isNullOrEmpty(text) && !OtherUtils.isNullOrEmpty(imageUrl)) {
            return false;
        } else if (OtherUtils.isNullOrEmpty(imageUrl)) {
            return false;
        } else {
            return true;
        }
    }

    @Ignore
    public boolean isPostAndTextAvailable() {
        if (!OtherUtils.isNullOrEmpty(text) && !OtherUtils.isNullOrEmpty(imageUrl)) {
            return true;
        } else {
            return false;
        }
    }

    @Ignore
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(view);
    }
}
