package com.example.sporteam.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sporteam.LocationDetailsActivity;
import com.example.sporteam.R;
import com.example.sporteam.model.Location;

import java.util.List;

public class LocationsAdapter extends ArrayAdapter<Location> {

    public LocationsAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final int pos = position;

        LayoutInflater inflater	= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View myRow = (convertView == null)
                ? inflater.inflate(R.layout.location_list_element, parent, false)
                : convertView;

        ImageView image = myRow.findViewById(R.id.locationImage);
        image.setImageResource(getItem(position).getImage());

        TextView name = myRow.findViewById(R.id.locationText);
        name.setText(getItem(position).getName());

        Button bookLocation = myRow.findViewById(R.id.locationDetailsButton);

        bookLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                final Location location = (Location) listView.getItemAtPosition(position);
                Intent intent = new Intent(v.getContext(), LocationDetailsActivity.class);
                intent.putExtra("location", location);
                v.getContext().startActivity(intent);
            }
        });

        return	myRow;
    }

}
