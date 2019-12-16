package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
