package com.example.rentaflat.flats;

import android.content.Context;

import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.data.RepositoryContract;
import com.example.rentaflat.database.FavoriteDatabase;
import com.example.rentaflat.database.FlatDatabase;

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
        void onFavBtnClicked();
        void fetchFlatsData();
        void onFlatClicked(FlatItem item);
    }

    interface Model {
        void fetchFlatsData(RepositoryContract.GetFlatsCallback callback);
        Context getContext();
    }

}