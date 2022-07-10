package com.example.rentaflat.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rentaflat.data.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_NAME = "user.db";
    private static volatile UserDatabase instance;

    static UserDatabase getInstance(Context context) {
        if(instance == null) instance = create(context);
        return instance;
    }

    private static UserDatabase create(Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
    }

    public abstract UserDao getUserDao();
}
