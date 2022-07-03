package com.example.rentaflat.flats;

import android.content.Context;
import android.util.Log;

import com.example.rentaflat.data.FlatRepository;
import com.example.rentaflat.data.RepositoryContract;
import com.example.rentaflat.database.FavoriteDatabase;
import com.example.rentaflat.database.FlatDatabase;

public class FlatsModel implements FlatsContract.Model {

    public static String TAG = FlatsModel.class.getSimpleName();


    private FlatRepository repository;
    //private FavoriteDatabase databaseFav;
    //private FlatDatabase databaseFlat;
    private Context context;

    public FlatsModel(FlatRepository repository, Context context) {
        this.repository = repository;
        this.context = context;

    }



    public void fetchFlatsData(final RepositoryContract.GetFlatsCallback callback) {
        Log.e(TAG, "fetchFlatsData()");

        repository.loadCatalog(false, new RepositoryContract.FetchFlatsDataCallback() {

            public void onFlatsDataFetched(boolean error) {
                if(!error) {
                    repository.getFlats(callback);
                }
            }
        });


    }

    public Context getContext() {
        return this.context;
    }



}