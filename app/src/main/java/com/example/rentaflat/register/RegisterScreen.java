package com.example.rentaflat.register;

import androidx.fragment.app.FragmentActivity;

import com.example.rentaflat.R;
import com.example.rentaflat.app.AppMediator;

import java.lang.ref.WeakReference;

public class RegisterScreen {

    public static void configure(RegisterContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        RegisterContract.Presenter presenter = new RegisterPresenter(mediator);
        RegisterContract.Model model = new RegisterModel(data, context.get());
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
