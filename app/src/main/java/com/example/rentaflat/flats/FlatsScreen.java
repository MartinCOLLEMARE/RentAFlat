package com.example.rentaflat.flats;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.rentaflat.R;
import com.example.rentaflat.app.AppMediator;
import com.example.rentaflat.data.FlatRepository;
import com.example.rentaflat.database.FavoriteDatabase;
import com.example.rentaflat.database.FlatDao;
import com.example.rentaflat.database.FlatDatabase;

import java.lang.ref.WeakReference;

public class FlatsScreen {

    public static void configure(FlatsContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        FlatRepository repository = FlatRepository.getInstance(context.get());


        FlatsContract.Presenter presenter = new FlatsPresenter(mediator);
        FlatsContract.Model model = new FlatsModel(repository, context.get());
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
