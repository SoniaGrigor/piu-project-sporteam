package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sporteam.model.Location;

import org.w3c.dom.Text;

public class LocationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        Intent intent = getIntent();

        Location location = (Location) intent.getSerializableExtra("location");

        TextView name = findViewById(R.id.locDetNameVal);
        TextView address = findViewById(R.id.locDetAddressVal);
        TextView owner = findViewById(R.id.locDetOwnerVal);
        TextView phone = findViewById(R.id.locDetPhoneVal);
        TextView availableFrom = findViewById(R.id.locDetAvFromVal);
        TextView availableTo = findViewById(R.id.locDetAvToVal);

        name.setText(location.getName());
        address.setText(location.getAddress());
        owner.setText(location.getOwnerName());
        phone.setText(location.getOwnerPhoneNumber());
        availableFrom.setText(location.getAvailableFrom().toString());
        availableTo.setText(location.getAvailableTo().toString());

    }
}
