package com.example.rentaflat.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite",
        primaryKeys = {"id_user", "id_flat"})
public class Favorite {

    @ForeignKey(entity = User.class, parentColumns = "id" ,childColumns = "id_user")
    public int id_user;

    @ForeignKey(entity =FlatItem.class, parentColumns = "id", childColumns ="id_flat")
    public int id_flat;

    public Favorite(int id_user, int id_flat) {
        this.id_flat = id_flat;
        this.id_user = id_user;
    }
}
