package com.example.rentaflat.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.rentaflat.database.FlatDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class FlatRepository implements RepositoryContract{

    public static String TAG = FlatRepository.class.getSimpleName();

    public static final String DB_FILE_FLATS = "flats.db";
    public static final String JSON_FILE = "flats.json";
    public static final String JSON_ROOT = "flats";

    private static FlatRepository INSTANCE;

    private FlatDatabase database;
    private Context context;

    public static FlatRepository getInstance(Context context) {
        if (INSTANCE ==null) INSTANCE = new FlatRepository(context);
        return INSTANCE;
    }

    private FlatRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, FlatDatabase.class, DB_FILE_FLATS).build();
    }

    public void loadCatalog(final boolean clearFirst, final FetchFlatsDataCallback callback) {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(clearFirst) {
                    database.clearAllTables();
                }

                boolean error = false;
                String out = "";
                //for (FlatItem flat : database.getFlatDao().getAllFlats()) {
                //    out += flat.toString() + "\n";
                //}
                //System.out.println(out);
                if(database.getFlatDao().getAllFlats().size() == 0 ) {
                    error =!loadCatalogFromJSON(loadJSONFromAsset());
                    Log.d(TAG, "Catalog loaded");

                }

                if(callback != null) {
                    callback.onFlatsDataFetched(error);
                }
            }
        });

    }

    public void getFlats(final GetFlatsCallback callback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(callback !=null) {
                    callback.setFlats(database.getFlatDao().getAllFlats());
                }
            }
        });
    }


    private boolean loadCatalogFromJSON(String json) {
        Log.e(TAG, "loadCatalogFromJSON()");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

            if (jsonArray.length() > 0) {

                final List<FlatItem> flats = Arrays.asList(
                        gson.fromJson(jsonArray.toString(), FlatItem[].class)
                );

                for (FlatItem flat: flats) {
                    database.getFlatDao().insert(flat);
                }

                return true;
            }

        } catch (JSONException error) {
            Log.e(TAG, "error: " + error);
        }

        return false;
    }

    private String loadJSONFromAsset() {
        //Log.e(TAG, "loadJSONFromAsset()");

        String json = null;

        try {

            InputStream is = context.getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException error) {
            Log.e(TAG, "error: " + error);
        }

        return json;
    }

}
