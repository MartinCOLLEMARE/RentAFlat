package com.example.rentaflat.flat;

import com.example.rentaflat.app.AppMediator;
import com.example.rentaflat.data.FlatItem;

import java.lang.ref.WeakReference;

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
    }

    public FlatItem getDataFromFlatList() {
        return mediator.getFlat();
    }

    public void fetchFlatData() {
        FlatItem flat = getDataFromFlatList();
        if(flat != null) {
            state.flat = flat;
        }
        view.get().displayFlatData(state);
    }

    public void onBookedBtnClicked() {
    }

    @Override
    public void onAddToFavBtnClicked() {
        state.addedToFavorites = !state.addedToFavorites;
        //getDataFromFlatList().setFavorite(!isAddedToFav); //if it already has been added, we have to delete it, otherwise we add it

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