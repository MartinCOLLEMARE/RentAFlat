package com.example.rentaflat.flats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.rentaflat.R;
import com.example.rentaflat.data.FlatItem;
import com.example.rentaflat.flats.FlatsActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FlatsAdapter extends BaseAdapter {

    private List<FlatItem> flatList;
    private LayoutInflater layoutInflater;
    private Context context;

    public FlatsAdapter( Context context, List<FlatItem> flatList) {
        this.context = context;
        this.flatList = flatList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return flatList.size();
    }

    @Override
    public FlatItem getItem(int i) {
        return flatList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.flat_list_content, null);
            holder = new ViewHolder();

            holder.imageView = view.findViewById(R.id.imageView_list);
            holder.name = view.findViewById(R.id.title_flat_list);
            holder.shortDescription = view.findViewById(R.id.flat_short_description);

            if(holder.imageView==null) System.out.println("image null");
            if(holder.shortDescription==null) System.out.println("desc null");
            if(holder.name==null) System.out.println("name null");

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        FlatItem flat = this.getItem(i);
        holder.name.setText(flat.name);
        holder.shortDescription.setText(flat.short_description);

        if(holder.imageView != null) loadImageFromURL(holder.imageView, flat.picture);

        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView shortDescription;
        TextView name;
    }

    public void loadImageFromURL(ImageView imageView, String imageUrl){
        //Executor executor = Executors.newSingleThreadExecutor();
        //Handler handler = new Handler(Looper.getMainLooper());
        //executor.execute(new Runnable() {
        //    @Override
        //    public void run() {

        //        try {
        //            InputStream in = new URL(imageUrl).openStream();
        //            Bitmap image = BitmapFactory.decodeStream(in);

        //            handler.post(new Runnable() {
        //@Override
        //                public void run() {
        //                    imageView.setImageBitmap(image);
        //                }
        //            });

        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //});
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }
}
