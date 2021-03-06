package com.example.rentaflat.flat;

import android.content.Context;

import java.lang.ref.WeakReference;

public interface FlatContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayFlatData(FlatViewModel viewModel);
        void onFinish();
    }

    interface Presenter {
        void onStart();
        void onRestart();
        void onResume();
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onBookedBtnClicked();
        void onReturnBtnClicked();
        void onAddToFavBtnClicked();


    }
    interface Model {
        Context getContext();
        void onDataFromNextScreen(String data);
        void onRestartScreen(String data);
        void onDataFromPreviousScreen(String data);
    }

}