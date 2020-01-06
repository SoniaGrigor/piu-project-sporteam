package com.example.sporteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sporteam.adapter.LocationsAdapter;
import com.example.sporteam.model.Location;
import com.example.sporteam.service.LocationService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class LocationBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_booking);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(LocationBookingActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(LocationBookingActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(LocationBookingActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(LocationBookingActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

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
