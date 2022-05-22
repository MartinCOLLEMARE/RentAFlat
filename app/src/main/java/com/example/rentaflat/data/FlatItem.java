package com.example.rentaflat.data;

public class FlatItem {

    public int id;
    public String short_description;
    public String long_description;
    public String name;
    public String picture;
    public boolean added_to_fav;

    public FlatItem(int id, String short_description, String long_description, String name, String picture, boolean added_to_fav) {
        this.id = id;
        this.short_description = short_description;
        this.long_description = long_description;
        this.name = name;
        this.picture = picture;
        this.added_to_fav = added_to_fav;
    }

    public void setFavorite(boolean added_to_fav) {
        this.added_to_fav = added_to_fav;
    }





}
