package com.example.rentaflat.app;

import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.flat.FlatState;
import com.example.rentaflat.flats.FlatsState;

import java.util.List;

@SuppressWarnings("unused")
public class AppMediator {

    private static AppMediator INSTANCE;

    private FlatState mFlatState;
    private FlatsState mFlatsState;

    private FlatItem flat;


    private AppMediator() {

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
     return mFlatState;
    }
    public FlatsState getFlatsState() { return mFlatsState;}


    public FlatItem getFlat() {
        FlatItem item = flat;
        return item;
    }


}

