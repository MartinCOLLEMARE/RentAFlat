package com.example.rentaflat.data;

import android.content.Context;
import android.os.AsyncTask;
import android.text.PrecomputedText;

import androidx.core.text.PrecomputedTextCompat;
import androidx.room.Room;

import com.example.rentaflat.database.FlatDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskFlats extends android.os.AsyncTask<Void, ArrayList<List<FlatItem>>,ArrayList<List<FlatItem>> > {

    private WeakReference<Context> context;
    private List<Integer> favorites;


    @Override
    protected ArrayList<List<FlatItem>> doInBackground(Void... voids) {
        FlatDatabase flatDatabase = Room.databaseBuilder(context.get(),FlatDatabase.class, "flats.db").build();
        List<FlatItem> favoriteFlats;
        if(favorites == null) {
            favoriteFlats = null;
        }
        else {
            favoriteFlats = flatDatabase.getFlatDao().getFlats(favorites);
        }
        ArrayList<List<FlatItem>> returnList = new ArrayList<>();
        returnList.add(flatDatabase.getFlatDao().getAllFlats());
        returnList.add(favoriteFlats);
        return returnList;
    }

    public AsyncTaskFlats(Context context, List<Integer> favorites) {
        this.context = new WeakReference<>(context);
        if(favorites == null) {
            this.favorites = null;
        }
        this.favorites = favorites;
    }
}
