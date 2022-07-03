package com.example.rentaflat.app;

import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.flat.FlatState;
import com.example.rentaflat.flats.FlatsState;

import java.util.List;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private FlatState flatState;
    private FlatsState flatsState;

    private FlatItem flat;


    private AppMediator() {
        flatsState = new FlatsState();

    }


    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }

    // to reset the state when testing
    public static void resetInstance() { INSTANCE = null; }

    public FlatState getFlatState() {
     return flatState;
    }
    public FlatsState getFlatsState() { return flatsState;}


    public FlatItem getFlat() {
        FlatItem item = flat;
        return item;
    }


}

