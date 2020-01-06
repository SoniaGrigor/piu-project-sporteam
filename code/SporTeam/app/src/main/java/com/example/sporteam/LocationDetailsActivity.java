package com.example.sporteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sporteam.model.Location;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class LocationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(LocationDetailsActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(LocationDetailsActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(LocationDetailsActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(LocationDetailsActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(LocationDetailsActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        Intent intent = getIntent();

        final Location location = (Location) intent.getSerializableExtra("location");

        TextView name = findViewById(R.id.locDetNameVal);
        TextView address = findViewById(R.id.locDetAddressVal);
        TextView owner = findViewById(R.id.locDetOwnerVal);
        TextView phone = findViewById(R.id.locDetPhoneVal);
        TextView availableFrom = findViewById(R.id.locDetAvFromVal);
        TextView availableTo = findViewById(R.id.locDetAvToVal);
        Button confirm = findViewById(R.id.bookButton);

        name.setText(location.getName());
        address.setText(location.getAddress());
        owner.setText(location.getOwnerName());
        phone.setText(location.getOwnerPhoneNumber());
        availableFrom.setText(location.getAvailableFrom().toString());
        availableTo.setText(location.getAvailableTo().toString());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("bookedLocation", location);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
