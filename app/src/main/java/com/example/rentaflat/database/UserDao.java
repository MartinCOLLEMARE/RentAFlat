package com.example.rentaflat.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rentaflat.data.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id=:id")
    User getUser(int id);

    @Query("SELECT * FROM user WHERE email=:email")
    User getUserByEmail(String email);

    @Insert
    void insert(User user);

    @Insert
    void insert(List<User> userList);

    @Update
    void update(User user);

}
