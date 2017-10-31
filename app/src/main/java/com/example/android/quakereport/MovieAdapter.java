package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class MovieAdapter extends ArrayAdapter<Movie> {


    public MovieAdapter(Activity context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    public View getView(int position, View convertview, ViewGroup parent) {
        View listItemView = convertview;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        Movie currentMovie1 = getItem(position);


        String originalLocation6 = currentMovie1.getPoster(); //this is the poster path


        ImageView pictureTV = (ImageView) listItemView.findViewById(R.id.primary_location1);


        Picasso.with(getContext()).load(originalLocation6).into(pictureTV);


        return listItemView;

    }


}
