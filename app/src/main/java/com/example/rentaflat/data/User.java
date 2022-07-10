package com.example.rentaflat.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.List;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String email;
    public String username;
    public String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public LinkedList<Boolean> acceptNewUser(java.util.List<User> userList) {
        boolean acceptedMail = true;
        boolean acceptedUsername = true;
        for(User user : userList) {
            if(user.email == this.email ) {
                acceptedMail = false;
            }
            if(user.username == this.username) {
                acceptedUsername = false;
            }
        }
        LinkedList<Boolean> returnList = new LinkedList<>();
        returnList.add(acceptedMail);
        returnList.add(acceptedUsername);


        return returnList;
    }

    public String toString() {
        String returnString = this.email+" ; " + this.password+" ; "+ this.username;
        return returnString;
    }
}
