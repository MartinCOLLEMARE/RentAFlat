package com.example.rentaflat.login;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.example.rentaflat.app.AppMediator;
import com.example.rentaflat.app.LoginToFlatsState;
import com.example.rentaflat.data.AsyncTaskFlats;
import com.example.rentaflat.data.AsyncTaskLogin;
import com.example.rentaflat.data.User;
import com.example.rentaflat.database.UserDatabase;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();

    private WeakReference<LoginContract.View> view;
    private LoginState state;
    private LoginContract.Model model;
    private AppMediator mediator;

    public LoginPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getLoginState();
    }

    public void onStart() {
        state.wrongPassword = false;
        state.wrongEmail = false;
    }


    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary



        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);
    }

    @Override
    public void onNoAccountBtnClicked() {
        view.get().navigateToRegisterScreen();
    }

    public void onLogInBtnClicked() {

        view.get().onDataUpdated(state);

        System.out.println("date updated");

        AsyncTaskLogin asyncTaskLogin = new AsyncTaskLogin(state.email, state.password, model.getContext());
        asyncTaskLogin.execute();
        LinkedList<Boolean> wrongs = new LinkedList<>();
        try {
            wrongs = asyncTaskLogin.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        state.wrongEmail = wrongs.get(0);
        state.wrongPassword = wrongs.get(1);

        System.out.println("AsyncTask executed" + state.wrongEmail + " ; " + state.wrongPassword);

        if(state.wrongEmail) {
            view.get().onDataUpdated(state);
            System.out.println(state.wrongEmail + " ; " + state.wrongPassword);

        }
        else {


            if (state.wrongPassword) {
                view.get().onDataUpdated(state);
            }
            else {
                LoginToFlatsState passDataToFlatsState = new LoginToFlatsState();
                passDataToFlatsState.id_user = state.id;
                view.get().navigateToFlatsScreen();

            }
        }
    }





    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

}