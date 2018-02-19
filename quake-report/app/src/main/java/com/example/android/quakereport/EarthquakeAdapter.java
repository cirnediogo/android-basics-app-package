package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Diogo on 09/06/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID eq_mag
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the magnitude from the current Earthquake object and
        // set this text on the magnitudeTextView
        magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitudeString()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = ContextCompat.getColor(getContext(), getMagnitudeColor(currentEarthquake.getMagnitude()));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView in the list_item.xml layout with the ID eq_offset
        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Get the offset location from the current Earthquake object and
        // set this text on the offsetTextView
        offsetTextView.setText(currentEarthquake.getLocationOffset());

        // Find the TextView in the list_item.xml layout with the ID eq_loc
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Get the location from the current Earthquake object and
        // set this text on the locationTextView
        locationTextView.setText(currentEarthquake.getPrimaryLocation());

        // Find the TextView in the list_item.xml layout with the ID eq_date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Get the date from the current Earthquake object and
        // set this text on the dateTextView
        dateTextView.setText(currentEarthquake.getDateString());

        // Find the TextView in the list_item.xml layout with the ID eq_time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Get the time from the current Earthquake object and
        // set this text on the timeTextView
        timeTextView.setText(currentEarthquake.getTimeString());

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;


        //Adapted from:
        //github.com/udacity/ud839_CustomAdapter_Example/blob/master/app/src/main/java/com/example/android/flavor/AndroidFlavorAdapter.java
    }

    public void openUrl(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getItem(position).getUrl()));
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            getContext().startActivity(intent);
        }
    }

    private int getMagnitudeColor(double magnitude) {
        if (magnitude >= 10.0) {
            return R.color.magnitude10plus;
        } else if (magnitude > 9.0) {
            return R.color.magnitude9;
        } else if (magnitude > 8.0) {
            return R.color.magnitude8;
        } else if (magnitude > 7.0) {
            return R.color.magnitude7;
        } else if (magnitude > 6.0) {
            return R.color.magnitude6;
        } else if (magnitude > 5.0) {
            return R.color.magnitude5;
        } else if (magnitude > 4.0) {
            return R.color.magnitude4;
        } else if (magnitude > 3.0) {
            return R.color.magnitude3;
        } else if (magnitude > 2.0) {
            return R.color.magnitude2;
        } else {
            return R.color.magnitude1;
        }
    }


}
