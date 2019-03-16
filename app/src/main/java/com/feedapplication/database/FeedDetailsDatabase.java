package com.feedapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.feedapplication.database.dao.FeedDetailsDao;
import com.feedapplication.database.entity.FeedDetails;

/**
 * Class is used to create database for store Entities.
 *
 * @author Shubham Chauhan
 */
@Database(entities = {FeedDetails.class}, version = 1)
public abstract class FeedDetailsDatabase extends RoomDatabase {

    public abstract FeedDetailsDao getFeedDetailsDao();

    private static FeedDetailsDatabase mProductDatabase;

    /**
     * Method is used to create a instance of room database.
     *
     * @param context context
     * @return instance of room database
     */
    public static FeedDetailsDatabase getInstance(final Context context) {
        if (mProductDatabase == null) {
            synchronized (FeedDetailsDatabase.class) {
                if (mProductDatabase == null) {
                    mProductDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            FeedDetailsDatabase.class, "feed_details_database")
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return mProductDatabase;
    }
}
