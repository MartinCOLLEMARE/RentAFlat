package com.example.rentaflat.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rentaflat.data.FlatItem;

@Database(entities = {FlatItem.class}, version = 1, exportSchema = false)
public abstract class FlatDatabase extends RoomDatabase {

    private static final String DB_NAME = "flats.db";
    private static volatile FlatDatabase instance;

    public static FlatDatabase getInstance(Context context) {
        if (instance == null) instance = create(context);
        return instance;
    }

    private static FlatDatabase create(Context context) {
        return Room.databaseBuilder(context, FlatDatabase.class, DB_NAME).build();
    }
    public abstract FlatDao getFlatDao();
}
