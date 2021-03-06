package com.example.rentaflat.flat;

import android.content.Context;

public class FlatModel implements FlatContract.Model {

    public static String TAG = FlatModel.class.getSimpleName();

    private Context context;

    public FlatModel(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        // Log.e(TAG, "getStoredData()");
        return context;
    }

    @Override
    public void onRestartScreen(String data) {
        // Log.e(TAG, "onRestartScreen()");
    }

    @Override
    public void onDataFromNextScreen(String data) {
        // Log.e(TAG, "onDataFromNextScreen()");
    }

    @Override
    public void onDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onDataFromPreviousScreen()");
    }
}