package com.example.rentaflat.flat;

import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.rentaflat.app.AppMediator;
import com.example.rentaflat.app.FlatsToFlatState;
import com.example.rentaflat.data.AsyncTaskFavorites;
import com.example.rentaflat.data.Favorite;
import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.database.FavoriteDatabase;

import java.lang.ref.WeakReference;
import java.sql.Savepoint;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FlatPresenter implements FlatContract.Presenter {

    public static String TAG = FlatPresenter.class.getSimpleName();

    private WeakReference<FlatContract.View> view;
    private FlatState state;
    private FlatContract.Model model;
    private AppMediator mediator;

    public FlatPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getFlatState();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {
        Log.d(TAG, "onRestart()");
    }

    public void onResume() {
        Log.d(TAG, "onResume()");

        FlatsToFlatState savedState = mediator.getFlatsToFlatState();
        if(savedState!= null) {
            state.flat = savedState.flat;
            state.userId = savedState.userID;

        }
        List<Integer> favorites = null;
        AsyncTaskFavorites asyncTaskFavorites = new AsyncTaskFavorites(model.getContext(), state.userId);
        asyncTaskFavorites.execute();
        try {
            favorites = asyncTaskFavorites.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            
        }

        if(state.flat != null) {

            if(favorites.contains(state.flat.id)) {
                state.addedToFavorites = true; 
            }
            else { 
                state.addedToFavorites = false;
            }
            
            System.out.println("fav : " +state.addedToFavorites);
        }

        view.get().displayFlatData(state);
    }



    public void onBookedBtnClicked() {
    }

    @Override
    public void onAddToFavBtnClicked() {
        state.addedToFavorites = !state.addedToFavorites;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                FavoriteDatabase favoriteDatabase = Room.databaseBuilder(model.getContext(), FavoriteDatabase.class, "favorites.db").build();
                if (state.addedToFavorites) {
                    favoriteDatabase.getFavoriteDao().insert(new Favorite(state.userId, state.flat.id));

                }
                else {
                    favoriteDatabase.getFavoriteDao().delete(new Favorite(state.userId, state.flat.id));
                }
            }
        });

        view.get().displayFlatData(state);
    }


    @Override
    public void onReturnBtnClicked() {
        view.get().onFinish();
    }

    public void injectView(WeakReference<FlatContract.View> view) {
        this.view = view;
    }

    public void injectModel(FlatContract.Model model) {
        this.model = model;
    }

}