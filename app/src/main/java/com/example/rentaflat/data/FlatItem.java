package com.example.rentaflat.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "flats")
public class FlatItem {

    @PrimaryKey
    public int id;

    public String short_description;
    public String long_description;
    public String name;
    public String picture;


    public FlatItem(int id, String short_description, String long_description, String name, String picture) {
        this.id = id;
        this.short_description = short_description;
        this.long_description = long_description;
        this.name = name;
        this.picture = picture;
    }

    public String toString() {
        String out = ""+ this.id + " ; " + this.name;
        return out;
    }










}
