package com.example.rentaflat.flat;

import androidx.fragment.app.FragmentActivity;

import com.example.rentaflat.R;
import com.example.rentaflat.app.AppMediator;

import java.lang.ref.WeakReference;

public class FlatScreen {

    public static void configure(FlatContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        FlatContract.Presenter presenter = new FlatPresenter(mediator);
        FlatContract.Model model = new FlatModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
