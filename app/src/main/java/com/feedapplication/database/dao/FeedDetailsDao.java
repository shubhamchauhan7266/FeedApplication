package com.feedapplication.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.feedapplication.database.entity.FeedDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * This Dao interface is used for define feed details operation.
 *
 * @author Shubham Chauhan
 */
@Dao
public interface FeedDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<FeedDetails> feedList);

    @Query("SELECT id, title, name, description, imageUrl, text, time, isLiked, isShowDate FROM feed_details")
    List<FeedDetails> getFeedDetailsList();

    @Query("SELECT id, title, name, description, imageUrl, text, time, isLiked, isShowDate FROM feed_details WHERE id = :id")
    FeedDetails getFeedDetails(int id);

    @Query("DELETE FROM feed_details")
    void deleteAll();

    @Query("UPDATE feed_details SET isLiked = :isLiked WHERE id = :id")
    void setLikeDislike(int id, boolean isLiked);
}
