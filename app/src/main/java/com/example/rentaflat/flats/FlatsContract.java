package com.example.rentaflat.flats;

import java.lang.ref.WeakReference;

public interface FlatsContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayFlatListData(FlatsViewModel viewModel);
        void navigateToFlatScreen();
    }

    interface Presenter {
        void onStart();
        void onRestart();
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
    }

    interface Model {
    }

}