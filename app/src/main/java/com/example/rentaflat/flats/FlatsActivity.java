package com.example.rentaflat.flats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
import com.example.rentaflat.flat.FlatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FlatsActivity
        extends AppCompatActivity implements FlatsContract.View {

    public static String TAG = FlatsActivity.class.getSimpleName();

    private FlatsContract.Presenter presenter;
    private Context context;
    private ListView flatList;
    private FrameLayout frameLayoutList;

    private static final String YELLOW_COLOR = "#FFEB3B";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_list);

        //getSupportActionBar().setTitle(R.string.app_name);
        // do the setup
        FlatsScreen.configure(this);
        //context = this.getBaseContext();

        if (savedInstanceState == null) {
            presenter.onStart();


        }
        presenter.fetchFlatsData();
    }

    public void onResume() {
        super.onResume();
        presenter.onResume();
    }



    public void navigateToFlatScreen(){

        Intent intent = new Intent(this, FlatActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayFlatListData(FlatsViewModel viewModel) {


        List<FlatItem> flats = viewModel.flats;

        ImageButton favBtn = findViewById(R.id.favorites_button);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlatItem flat =(FlatItem) view.getTag();
                presenter.onFlatClicked(flat);

            }
        };
        favBtn.setColorFilter(android.R.color.white);

        if(viewModel.favoritesSelected) {

            favBtn.setColorFilter(Color.parseColor(YELLOW_COLOR));

            List<FlatItem> flatsBis = flats;
            if(viewModel.favoriteFlats == null) {
                flats = null;
            }
            else {
                flats = viewModel.favoriteFlats;
            }
        }


        int counter = 0;
        if(flats != null ) {

            final ListView listView = findViewById(R.id.listView_flats);
            listView.setAdapter(new FlatsAdapter(this, flats));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Object o = listView.getItemAtPosition(position);
                    FlatItem flatSelected = (FlatItem)o;
                    presenter.onFlatClicked(flatSelected);
                }
            });

        }
    }
    public void onFavBtnClicked(View view ) {
        presenter.onFavBtnClicked();
    }



    @Override
    public void injectPresenter(FlatsContract.Presenter presenter) {
        this.presenter = presenter;
    }


}