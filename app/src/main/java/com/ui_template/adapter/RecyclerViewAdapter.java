package com.ui_template.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pkmmte.view.CircularImageView;
import com.ui_template.R;
import com.ui_template.model.Drivers;
import com.ui_template.util.TextGetter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
//import com.pkmmte.view.CircularImageView;

/**
 * Created by funmiayinde on 1/13/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TextGetter {
    private Context context;
    private List<Drivers> dataSet;


    public RecyclerViewAdapter (List<Drivers> contacts, Context c) {
        this.dataSet = contacts;
        this.context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_details, parent, false);

        return new DriversViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DriversViewHolder contactHolder = (DriversViewHolder) holder;
        contactHolder.contactName.setText(dataSet.get(position).getName());
        contactHolder.firstLetter.setText(String.valueOf(dataSet.get(position).getName().charAt(0)).toUpperCase());

        if (dataSet.get(position).getImage() != null) {
            contactHolder.firstLetter.setVisibility(TextView.INVISIBLE);
            String imgUrl = "https://api.androidhive.info/images/glide/medium/deadpool.jpg";
            Glide.with(context).load(dataSet.get(position).getImage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(contactHolder.thumbnail);
        } else {
            contactHolder.firstLetter.setVisibility(TextView.VISIBLE);
            contactHolder.thumbnail.setImageResource(R.drawable.circle_icon);

            GradientDrawable drawable = (GradientDrawable) contactHolder.thumbnail.getDrawable();
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            drawable.setColor(color);
        }

        setRegularLineLayout(contactHolder);
    }

    private void setFirstLineTextLayout (DriversViewHolder vh) {
        vh.firstLetter.setText("Set up my profile");
        vh.firstLetter.setTextColor(Color.parseColor("#000000"));
        vh.firstLetter.setTextSize(18);
        vh.contactName.setText("");
        vh.thumbnail.setVisibility(CircularImageView.INVISIBLE);
    }

    private void setRegularLineLayout(DriversViewHolder vh) {
        vh.firstLetter.setTextColor(Color.parseColor("#ffffff"));
        vh.firstLetter.setTextSize(26);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public Drivers getContact (int pos) {
        return dataSet.get(pos);
    }

    public Drivers getContactByName (String name) {
        for (Drivers c : dataSet) {
            if (name.equals(c.getName())) {
                return c;
            }
        }

        return null;
    }

    public List<Drivers> getDataSet() {
        return dataSet;
    }


    @Override
    public String getTextFromAdapter(int pos) {
        return String.valueOf(dataSet.get(pos).getName().charAt(0)).toUpperCase();
    }

    public class DriversViewHolder extends RecyclerView.ViewHolder {
        TextView firstLetter;
        TextView contactName;
        CircleImageView thumbnail;

        public DriversViewHolder(View v) {
            super (v);
            firstLetter = (TextView) v.findViewById(R.id.contact_first_letter);
            contactName = (TextView) v.findViewById(R.id.driver_name);
            thumbnail = (CircleImageView) v.findViewById(R.id.driver_thumbnail);
        }
    }
}
