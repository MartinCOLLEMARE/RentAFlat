package com.example.rentaflat.flats;

import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.rentaflat.app.AppMediator;
import com.example.rentaflat.app.FlatsToFlatState;
import com.example.rentaflat.app.LoginToFlatsState;
import com.example.rentaflat.app.RegisterToFlatsState;
import com.example.rentaflat.data.AsyncTaskFavorites;
import com.example.rentaflat.data.AsyncTaskFlats;
import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.data.RepositoryContract;
import com.example.rentaflat.database.FavoriteDatabase;
import com.example.rentaflat.database.FlatDatabase;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FlatsPresenter implements FlatsContract.Presenter {

    public static String TAG = FlatsPresenter.class.getSimpleName();
    private static List<Integer> favorites;

    private WeakReference<FlatsContract.View> view;
    private FlatsState state;
    private FlatsContract.Model model;
    private AppMediator mediator;

    //private FavoriteDatabase databaseFav;
    //private FlatDatabase databaseFlat;

    public FlatsPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getFlatsState();

    }

    public void onStart() {
        Log.d(TAG, "onStart()");
        state.favoritesSelected = false;

        RegisterToFlatsState savedState = mediator.getRegisterToFlatState();
        if(savedState!= null) {
            state.userId = savedState.userid;
        }
        else {
            LoginToFlatsState saveState = mediator.getLoginToFlatsState();
            state.userId = savedState.userid;
        }

    }


    public void onResume() {
        Log.d(TAG, "onResume()");
        fetchFlatsData();
        view.get().displayFlatListData(state);
    }

    @Override
    public void injectView(WeakReference<FlatsContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(FlatsContract.Model model) {
        this.model = model;
    }

    @Override
    public void onFavBtnClicked() {
        state.favoritesSelected = !state.favoritesSelected;
        view.get().displayFlatListData(state);
    }

    @Override
    public void fetchFlatsData() {

        Log.d(TAG, "presenter.fetchFlatsData()");
        //databaseFav = model.getDatabaseFav();
        //databaseFlat = model.getDatabaseFlat();

        model.fetchFlatsData(new RepositoryContract.GetFlatsCallback() {
            @Override
            public void setFlats(List<FlatItem> flats) {
                state.flats = flats;
            }
        });
        //the fetchFlatsData method doesn't seem to work, that's why I try to access my Flats another way
        //FlatDatabase databaseFlat = Room.databaseBuilder(model.getContext(), FlatDatabase.class, "flats.db" ).build();
        //AsyncTask.execute(new Runnable() {
        //    @Override
        //    public void run() {
        //        state.flats = databaseFlat.getFlatDao().getAllFlats();
        //        String str = "";
        //        for (FlatItem flat : state.flats) {

        //            str += flat.toString() + "\n";
        //        }
        //        System.out.println(str);
        //    }
        //});

        AsyncTaskFavorites asyncTaskFavorites = new AsyncTaskFavorites(model.getContext(), state.userId);
        asyncTaskFavorites.execute();
        favorites = null;
        try {
            favorites = asyncTaskFavorites.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        //running favorites async first cause we need the favorites list

        AsyncTaskFlats asyncTaskFlats = new AsyncTaskFlats(model.getContext(), favorites);
        asyncTaskFlats.execute();
        try {
            state.flats = asyncTaskFlats.get().get(0);
            state.favoriteFlats = asyncTaskFlats.get().get(1);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "state.favoriteFlats updated");


        //System.out.println(state.flats !=null);

        if(state.flats != null) {
            String str = "";
            for (FlatItem flat : state.flats) {
                str += flat.toString() + "\n";
            }
            System.out.println(str);
        }

        view.get().displayFlatListData(state);
    }

    @Override
    public void onFlatClicked(FlatItem item) {
        Log.d(TAG, "onFlatClicked" + item.id);
        FlatsToFlatState passData = new FlatsToFlatState();
        passData.flat = item;
        passData.userID = state.userId;
        mediator.setFlatsToFlatState(passData);
        view.get().navigateToFlatScreen();
    }

}