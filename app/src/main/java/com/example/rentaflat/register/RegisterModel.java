package com.example.rentaflat.register;

import android.content.Context;

public class RegisterModel implements RegisterContract.Model {

    public static String TAG = RegisterModel.class.getSimpleName();

    private String data;
    private Context context;

    public RegisterModel(String data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }


}