package com.example.rentaflat.data;

import android.content.Context;
import android.os.AsyncTask;
import android.text.PrecomputedText;

import androidx.core.text.PrecomputedTextCompat;
import androidx.room.Room;

import com.example.rentaflat.database.FlatDatabase;

import java.lang.ref.WeakReference;
import java.util.List;

public class AsyncTaskApp extends android.os.AsyncTask<Void, List<FlatItem> ,List<FlatItem> > {

    private WeakReference<Context> context;


    @Override
    protected List<FlatItem> doInBackground(Void... voids) {
        FlatDatabase flatDatabase = Room.databaseBuilder(context.get(),FlatDatabase.class, "flats.db").build();
        return flatDatabase.getFlatDao().getAllFlats();
    }

    public AsyncTaskApp(Context context ) {
        this.context = new WeakReference<>(context);
    }
}
