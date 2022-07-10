package com.example.rentaflat.login;

import android.content.Context;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private Context context;

    public LoginModel(Context context) {
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