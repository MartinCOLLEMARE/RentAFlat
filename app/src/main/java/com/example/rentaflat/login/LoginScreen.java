package com.example.rentaflat.login;

import androidx.fragment.app.FragmentActivity;

import com.example.rentaflat.R;
import com.example.rentaflat.app.AppMediator;

import java.lang.ref.WeakReference;

public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        LoginContract.Presenter presenter = new LoginPresenter(mediator);
        LoginContract.Model model = new LoginModel(context.get());
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
