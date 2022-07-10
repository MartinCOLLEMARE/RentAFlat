package com.example.rentaflat.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import java.util.List;
import java.util.LinkedList;

import com.example.rentaflat.database.UserDatabase;
import com.example.rentaflat.data.User;

public class AsyncTaskRegister extends AsyncTask<Void, Void , LinkedList<Boolean>> {

    private User user;
    private Context context;

    public AsyncTaskRegister(User user, Context context) {

        this.user=user;
        this.context = context;
    }

    @Override
    protected  LinkedList<Boolean> doInBackground(Void... voids) {
        UserDatabase userDatabase = Room.databaseBuilder(context, UserDatabase.class, "user.db").build();
        List<User> userList = userDatabase.getUserDao().getAllUsers();
        LinkedList<Boolean> accepted = user.acceptNewUser(userList);
        return accepted;
    }




}
