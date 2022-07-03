package com.example.rentaflat.flats;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.rentaflat.app.AppMediator;
import com.example.rentaflat.app.FlatsToFlatState;
import com.example.rentaflat.data.AsyncTaskApp;
import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.data.RepositoryContract;
import com.example.rentaflat.database.FavoriteDatabase;
import com.example.rentaflat.database.FlatDatabase;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FlatsPresenter implements FlatsContract.Presenter {

    public static String TAG = FlatsPresenter.class.getSimpleName();

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
        state.favoritesSelected = false;

    }
    public void onRestart(){}

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
        state.favoritesSelected = true;
    }

    @Override
    public void fetchFlatsData() {
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

        AsyncTaskApp asyncTaskApp = new AsyncTaskApp(model.getContext());
        asyncTaskApp.execute();
        try {
            state.flats = asyncTaskApp.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(state.flats !=null);

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
        FlatsToFlatState passData = new FlatsToFlatState();
        passData.flat = item;
        view.get().navigateToFlatScreen();
    }

}