package com.example.rentaflat.flat;

import java.lang.ref.WeakReference;

public interface FlatContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void navigateToNextScreen();
        void displayFlatData(FlatViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onBookedBtnClicked();
        void onReturnBtnClicked();
        void onAddToFavBtnClicked();
    }
    interface Model {
        String getStoredData();
        void onDataFromNextScreen(String data);
        void onRestartScreen(String data);
        void onDataFromPreviousScreen(String data);
    }

}