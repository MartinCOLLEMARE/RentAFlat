package com.example.rentaflat.flats;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.rentaflat.R;
import com.example.rentaflat.app.FlatsToFlatState;
import com.example.rentaflat.data.Favorite;
import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.database.FavoriteDatabase;
import com.example.rentaflat.database.FlatDatabase;

import java.util.List;

public class FlatsActivity
        extends AppCompatActivity implements FlatsContract.View {

    public static String TAG = FlatsActivity.class.getSimpleName();

    private FlatsContract.Presenter presenter;
    private Context context;
    private ListView flatList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_list);
        setContentView(R.layout.flat_list);
        setContentView(R.layout.flat_list_content);
        getSupportActionBar().setTitle(R.string.app_name);
        // do the setup
        FlatsScreen.configure(this);
        //context = this.getBaseContext();

        flatList = (ListView) findViewById(R.id.flat_list);
        if(flatList == null) {
            System.out.println("flatList broken");
        }


        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
        presenter.fetchFlatsData();
    }

    public void navigateToFlatScreen(){

        Intent intent = new Intent(this, FlatsActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayFlatListData(FlatsViewModel viewModel) {

        List<Integer> favoritesFlats = viewModel.favoriteFlats;
        List<FlatItem> flats = viewModel.flats;


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlatItem item =(FlatItem) view.getTag();
                presenter.onFlatClicked(item);

            }
        };
        if(viewModel.favoritesSelected) {
            List<FlatItem> flatsBis = flats;
            for(FlatItem flat : flatsBis) {
                if(!favoritesFlats.contains(flat.id)) {
                    flats.remove(flat);
                }
            }
        }

        int counter = 0;

        if(flats != null ) {
            for (FlatItem flat : flats) {
                LinearLayout flatContent = findViewById(R.id.flat_content);

                TextView flatTitle = findViewById(R.id.title_flat);
                if(flatTitle == null ){
                    System.out.println("Broken title");
                }
                flatTitle.setText(flat.name);

                TextView flatShortDesc = findViewById(R.id.flat_short_description);
                flatShortDesc.setText(flat.short_description);

                ImageView imageView = findViewById(R.id.imageView_list);
                if(imageView == null ){
                    System.out.println("Broken");
                }
                loadImageFromURL(imageView, flat.picture);

                flatContent.setOnClickListener(onClickListener);
                flatContent.setTag(counter);

                //flatList = findViewById(R.id.flat_list);
                if(flatList == null ) {
                    System.out.println("flatList broken");
                }
                flatList.addFooterView(flatContent);
                counter++;
            }
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
    public void injectPresenter(FlatsContract.Presenter presenter) {
        this.presenter = presenter;
    }


}