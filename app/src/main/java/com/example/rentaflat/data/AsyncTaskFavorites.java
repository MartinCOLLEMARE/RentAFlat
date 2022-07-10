package com.example.rentaflat.data;

import android.content.Context;

import androidx.room.Room;

import com.example.rentaflat.database.FavoriteDatabase;

import java.lang.ref.WeakReference;
import java.util.List;

public class AsyncTaskFavorites extends android.os.AsyncTask<Void, List<Integer>, List<Integer>> {

    private WeakReference<Context> context;
    private int userId;

    @Override
    protected List<Integer> doInBackground(Void... voids) {
        FavoriteDatabase favoriteDatabase = Room.databaseBuilder(context.get(), FavoriteDatabase.class, "favorites.db").build();
        return favoriteDatabase.getFavoriteDao().getUserFavorites(userId);
    }

    public AsyncTaskFavorites(Context context, int id) {
        this.context = new WeakReference<>(context);
        this.userId = id;
    }
}
