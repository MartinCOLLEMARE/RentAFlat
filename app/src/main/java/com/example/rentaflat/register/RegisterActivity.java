package com.example.rentaflat.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.rentaflat.R;
import com.example.rentaflat.data.User;
import com.example.rentaflat.database.UserDatabase;
import com.example.rentaflat.flats.FlatsActivity;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    public static String TAG = RegisterActivity.class.getSimpleName();

    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().setTitle(R.string.app_name);


        // do the setup
        RegisterScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }


    @Override
    public void onDataUpdated(RegisterViewModel viewModel) {
        Log.d(TAG, "onDataUpdated()");

        EditText email = findViewById(R.id.email_inscription_editText);
        EditText username = findViewById(R.id.username_inscription_editText);
        EditText password = findViewById(R.id.password_inscription_editText);

        if(!viewModel.emailAvailable) {
            Toast toast = Toast.makeText(this, "Email not available", Toast.LENGTH_LONG);
            toast.show();
            email.getText().clear();
        }
        if(!viewModel.usernameAvailable) {
            Toast toast = Toast.makeText(this,"Username not available", Toast.LENGTH_LONG);
            toast.show();
            username.getText().clear();
        }

        else {
            // deal with the data
            if (email.getText() != null & username.getText() != null & password.getText() != null) {
                viewModel.fieldsCompleted = true;
                viewModel.user = new User(email.getText().toString(), username.getText().toString(), password.getText().toString());
                System.out.println(viewModel.user.toString());
            } else {
                viewModel.fieldsCompleted = false;
                Toast toast = Toast.makeText(this, "Please fill all the blanks", Toast.LENGTH_LONG);
                toast.show();
            }
        }


    }

    public void onSignInBtnClicked(View view) {
        presenter.onSignInBtnClicked();
    }

    //public boolean fieldsCompleted() {
    //
    //}


    @Override
    public void navigateToFlatsScreen() {
        Intent intent = new Intent(this, FlatsActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}