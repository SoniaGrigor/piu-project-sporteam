package com.example.sporteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sporteam.model.Equipment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EquipmentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(EquipmentDetailsActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(EquipmentDetailsActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(EquipmentDetailsActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(EquipmentDetailsActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(EquipmentDetailsActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        Intent intent = getIntent();

        final Equipment equipment = (Equipment) intent.getSerializableExtra("equipment");

        TextView itemName = findViewById(R.id.itemName);
        ImageView itemImage = findViewById(R.id.itemImage);
        TextView itemDescription = findViewById(R.id.itemDescription);
        TextView itemPrice = findViewById(R.id.itemPrice);
        Button confirm = findViewById(R.id.buyItem);

        itemName.setText(equipment.getName());
        itemImage.setImageResource(equipment.getImage());
        itemDescription.setText(equipment.getDescription());
        itemPrice.setText(equipment.getPrice() + " RON");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}
