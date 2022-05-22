package com.example.rentaflat.flats;

import androidx.fragment.app.FragmentActivity;

import com.example.rentaflat.R;
import com.example.rentaflat.app.AppMediator;

import java.lang.ref.WeakReference;

public class FlatsScreen {

    public static void configure(FlatsContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        FlatsContract.Presenter presenter = new FlatsPresenter(mediator);
        FlatsContract.Model model = new FlatsModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
