package com.example.rentaflat.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.rentaflat.database.UserDatabase;

import java.util.LinkedList;

public class AsyncTaskLogin extends AsyncTask<Void, Void, LinkedList<Boolean>> {

    private String email;
    private String password;
    private Context context;

    public AsyncTaskLogin(String email, String password, Context context) {
        this.email = email;
        this.password = password;
        this.context = context;
    }

    @Override
    protected LinkedList<Boolean> doInBackground(Void... voids) {
        UserDatabase userDatabase = Room.databaseBuilder(context, UserDatabase.class, "user.db").build();
        System.out.println("database gotten");


        Boolean wrongEmail = !userDatabase.getUserDao().userExists(email);
        Boolean wrongPassword = null;
        if(!wrongEmail) {
            wrongPassword = !(userDatabase.getUserDao().getUserByEmail(email).password.equals(password));
        } else {
            wrongPassword = false;
        }
        LinkedList<Boolean> returnList = new LinkedList<>();
        returnList.add(wrongEmail);
        returnList.add(wrongPassword);
        return returnList;
    }
}
