package com.example.rentaflat.register;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.room.Room;

import com.example.rentaflat.app.AppMediator;
import com.example.rentaflat.app.RegisterToFlatsState;
import com.example.rentaflat.data.AsyncTaskRegister;
import com.example.rentaflat.database.UserDatabase;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class RegisterPresenter implements RegisterContract.Presenter {

    public static String TAG = RegisterPresenter.class.getSimpleName();

    private WeakReference<RegisterContract.View> view;
    private RegisterState state;
    private RegisterContract.Model model;
    private AppMediator mediator;

    public RegisterPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getRegisterState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state
        state = new RegisterState();

        // call the model and update the state

        // use passed state if is necessary
        state.emailAvailable = true;
        state.usernameAvailable = true;

    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary


        fetchRegisterData();

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

    }

    private void fetchRegisterData() {

        //UserDatabase userDatabase = Room.databaseBuilder(model.getContext(), UserDatabase.class, "users.db").build();

    }

    @Override
    public void onSignInBtnClicked() {
        Log.d(TAG, "onSignInBtnClicked()");


        if(state.fieldsCompleted) {

            AsyncTaskRegister asyncTaskRegister = new AsyncTaskRegister(state.user, model.getContext());
            asyncTaskRegister.execute();
            LinkedList<Boolean> accepted = new LinkedList<>();
            try {
                accepted = asyncTaskRegister.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            //email & username accepted
            if(accepted.get(0) & accepted.get(1)) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        UserDatabase userDatabase = Room.databaseBuilder(model.getContext(), UserDatabase.class, "user.db").build();
                        userDatabase.getUserDao().insert(state.user);

                    }
                });

                RegisterToFlatsState passDataToFlatsState = new RegisterToFlatsState();
                passDataToFlatsState.userid = state.user.id;
                mediator.setRegisterToFlatState(passDataToFlatsState);

                view.get().navigateToFlatsScreen();

            }
            else {
                if(!accepted.get(0)) {
                    state.emailAvailable = false;
                }
                if(!accepted.get(1)) {
                    state.usernameAvailable = false;
                }
                view.get().onDataUpdated(state);
            }

        }
        else{ Log.d(TAG, "Fields not completed");}
            view.get().onDataUpdated(state);

    }


    @Override
    public void injectView(WeakReference<RegisterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RegisterContract.Model model) {
        this.model = model;
    }

}