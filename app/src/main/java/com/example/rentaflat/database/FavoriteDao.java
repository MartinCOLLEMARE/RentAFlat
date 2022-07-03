package com.example.rentaflat.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rentaflat.data.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    List<Favorite> getAllFavorites();

    @Query("SELECT id_flat FROM favorite WHERE id_user=:id")
    List<Integer> getUserFavorites(int id);

    @Insert
    void insert(Favorite favorite);

    @Update
    void update(Favorite favorite);
}
