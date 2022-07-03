package com.example.rentaflat.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rentaflat.data.FlatItem;

import java.util.List;

@Dao
public interface FlatDao {

    public static boolean TRUE = true;

    @Query("SELECT * FROM flats")
    List<FlatItem> getAllFlats();

    @Query("SELECT * FROM flats WHERE id=:id")
    FlatItem getFlat(int id);

    @Insert
    void insert(FlatItem flat);


}
