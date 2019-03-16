package com.feedapplication.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

/**
 * This Entity class is used to create a table for feed Details.
 *
 * @author Shubham Chauhan
 */
@Entity(tableName = "feed_details")
public class FeedDetails {

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
}
