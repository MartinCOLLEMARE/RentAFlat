package com.example.rentaflat.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentaflat.R;
import com.example.rentaflat.data.User;
import com.example.rentaflat.flats.FlatsActivity;
import com.example.rentaflat.register.RegisterActivity;

import org.w3c.dom.Text;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().setTitle(R.string.app_name);


        // do the setup
        LoginScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }


    @Override
    public void onDataUpdated(LoginViewModel viewModel) {
        Log.d(TAG, "onDataUpdated()");

        EditText email = findViewById(R.id.email_connexion_editText);
        EditText password = findViewById(R.id.password_connexion_editText);


        if(email.getText() != null & password.getText()!=null) {

            if(viewModel.wrongEmail) {
                Toast toast = Toast.makeText(this,"This email doesn't exist in our database", Toast.LENGTH_LONG);
                toast.show();
                email.getText().clear();
                password.getText().clear();
                viewModel.wrongEmail = false;
                System.out.println("Activity wrong email");
            }

            else if (viewModel.wrongPassword) {
                Toast toast = Toast.makeText(this, "Wrong password", Toast.LENGTH_LONG);
                toast.show();
                password.getText().clear();
                viewModel.wrongPassword = false;

            }


            else{

            //viewModel.fieldsCompleted = true;
            viewModel.email = email.getText().toString();
            viewModel.password = password.getText().toString();
            }
        }
        else { //viewModel.fieldsCompleted = false;
            Toast toast = Toast.makeText(this,"Please fill all the blanks", Toast.LENGTH_LONG);
            toast.show();
        }
        //presenter.fetchLoginData();

    }

    public void onNoAccountBtnClicked(View view) {
        presenter.onNoAccountBtnClicked();
    }

    public void onLogInBtnClicked(View view) {
        presenter.onLogInBtnClicked();
    }


    public void navigateToFlatsScreen() {
        Intent intent = new Intent(this, FlatsActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void navigateToRegisterScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}