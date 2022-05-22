package com.example.rentaflat.flats;

import android.content.Intent;

import com.example.rentaflat.app.AppMediator;

import java.lang.ref.WeakReference;

public class FlatsPresenter implements FlatsContract.Presenter {

    public static String TAG = FlatsPresenter.class.getSimpleName();

    private WeakReference<FlatsContract.View> view;
    private FlatsState state;
    private FlatsContract.Model model;
    private AppMediator mediator;

    public FlatsPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getFlatsState();
    }

    public void onStart() {}
    public void onRestart(){}

    @Override
    public void injectView(WeakReference<FlatsContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(FlatsContract.Model model) {
        this.model = model;
    }

}