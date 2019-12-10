package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sporteam.adapter.EventAdapter;
import com.example.sporteam.adapter.LocationsAdapter;
import com.example.sporteam.model.Location;
import com.example.sporteam.service.EventService;
import com.example.sporteam.service.LocationService;

import java.util.List;

public class LocationBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_booking);

        List<Location> locations = LocationService.getLocations();

        LocationsAdapter adapter = new LocationsAdapter(this, locations);
        final ListView listView = (ListView) findViewById(R.id.locationList);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    public void LocationToRent(View view) {

        Intent intent = new Intent(this, LocationForRentActivity.class);
        startActivity(intent);

    }

}
