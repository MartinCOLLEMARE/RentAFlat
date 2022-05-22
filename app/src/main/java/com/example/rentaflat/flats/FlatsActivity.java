package com.example.rentaflat.flats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentaflat.R;

public class FlatsActivity
        extends AppCompatActivity implements FlatsContract.View {

    public static String TAG = FlatsActivity.class.getSimpleName();

    private FlatsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_list);
        getSupportActionBar().setTitle(R.string.app_name);


        // do the setup
        FlatsScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }

    public void navigateToFlatScreen(){
        Intent intent = new Intent(this, FlatsActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayFlatListData(FlatsViewModel viewModel) {

    }


    @Override
    public void injectPresenter(FlatsContract.Presenter presenter) {
        this.presenter = presenter;
    }


}