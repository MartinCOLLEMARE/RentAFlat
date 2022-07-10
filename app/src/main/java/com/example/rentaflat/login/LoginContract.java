package com.example.rentaflat.login;

import android.content.Context;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(LoginViewModel viewModel);

        void navigateToFlatsScreen();

        void navigateToRegisterScreen();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onStart();

        void onResume();

        void onLogInBtnClicked();

        void onNoAccountBtnClicked();

    }

    interface Model {
        Context getContext();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}