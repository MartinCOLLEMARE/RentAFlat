package com.example.rentaflat.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rentaflat.data.Favorite;

@Database(entities = {Favorite.class}, version = 1, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static final String DB_NAME = "favorite.db";
    private static volatile FavoriteDatabase instance;

    public static FavoriteDatabase getInstance(Context context) {
        if(instance ==null) instance = create(context);
        return instance;
    }

    private static FavoriteDatabase create(Context context) {
        return Room.databaseBuilder(context, FavoriteDatabase.class, DB_NAME).build();
    }

    public abstract FavoriteDao getFavoriteDao();

}
