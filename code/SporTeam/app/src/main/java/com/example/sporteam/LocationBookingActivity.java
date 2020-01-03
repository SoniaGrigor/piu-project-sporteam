package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sporteam.adapter.LocationsAdapter;
import com.example.sporteam.model.Location;
import com.example.sporteam.service.LocationService;

import java.util.List;

public class LocationBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_booking);

        Button locBtn = findViewById(R.id.placesButton);
        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocationBookingActivity.class);
                startActivity(intent);
            }
        });

        Button eqBtn = findViewById(R.id.equipmentButton);
        eqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EquipmentsActivity.class);
                startActivity(intent);
            }
        });


        List<Location> locations = LocationService.getInstance().getLocations();

        LocationsAdapter adapter = new LocationsAdapter(this, locations);
        final ListView listView = findViewById(R.id.locationList);
        listView.setAdapter(adapter);

    }

    public void LocationToRent(View view) {
        Intent intent = new Intent(this, LocationForRentActivity.class);
        startActivityForResult(intent, 2);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        switch(requestCode) {
            case (1): {
                if (resultCode == Activity.RESULT_OK) {
                    Location location = (Location) data.getSerializableExtra("bookedLocation");
                    ListView listView = findViewById(R.id.locationList);
                    LocationsAdapter adapter = (LocationsAdapter) listView.getAdapter();
                    listView.setAdapter(adapter);
                    LocationService.getInstance().removeLocation(location);
                    System.out.println(location.getName());
                    adapter.notifyDataSetChanged();
                }
            }
            break;
            case (2): {
                if (resultCode == Activity.RESULT_OK) {
                    Location location = (Location) data.getSerializableExtra("rentLocation");
                    ListView listView = findViewById(R.id.locationList);
                    LocationsAdapter adapter = (LocationsAdapter) listView.getAdapter();
                    listView.setAdapter(adapter);
                    LocationService.getInstance().addLocation(location);
                    System.out.println(location.getName());
                    adapter.notifyDataSetChanged();
                }
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
