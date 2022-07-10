package com.example.rentaflat.register;

import android.content.Context;

import java.lang.ref.WeakReference;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(RegisterViewModel viewModel);

        void navigateToFlatsScreen();

        //boolean fieldsCompleted();


    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onSignInBtnClicked();


    }

    interface Model {
        Context getContext();


    }

}