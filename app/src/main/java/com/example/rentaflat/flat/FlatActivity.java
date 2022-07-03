package com.example.rentaflat.flat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.example.rentaflat.R;
import com.example.rentaflat.data.FlatItem;

public class FlatActivity
        extends AppCompatActivity implements FlatContract.View {

    public static String TAG = FlatActivity.class.getSimpleName();

    private FlatContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat);
        getSupportActionBar().setTitle(R.string.app_name);


        // do the setup
        FlatScreen.configure(this);



        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
    }



    private void loadImageFromURL(ImageView imageView, String imageUrl){
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }



    @Override
    public void displayFlatData(FlatViewModel viewModel) {
        Log.e(TAG,"displayFlatData");

        FlatItem flat = viewModel.flat;

        if(flat!=null) {
            TextView headTextView = findViewById(R.id.head_textView);
            headTextView.setText(flat.name);

            TextView descriptionTextView = findViewById(R.id.flat_description_textView);
            descriptionTextView.setText(flat.long_description);

            loadImageFromURL(findViewById(R.id.flat_imageView), flat.picture);

            Button addToFavBtn = findViewById(R.id.addToFavorite_button);


            if(true) {
                addToFavBtn.setText(R.string.added_to_fav_text);
            }
            else {
                addToFavBtn.setText(R.string.addToFavorite_text);
            }

        }


    }

    @Override
    public void onFinish() {
        finish();
    }

    public void onBookedBtnClicked(View view) {
        presenter.onBookedBtnClicked();
    }
    public void onReturnBtnClicked(View view) {
        presenter.onReturnBtnClicked();
    }
    public void onAddToFavBtnClicked(View view) {
        presenter.onAddToFavBtnClicked();
    }

    @Override
    public void injectPresenter(FlatContract.Presenter presenter) {
        this.presenter = presenter;
    }


}