package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sporteam.adapter.EquipmentsAdapter;
import com.example.sporteam.model.Equipment;
import com.example.sporteam.service.EquipmentService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class EquipmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(EquipmentsActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(EquipmentsActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(EquipmentsActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(EquipmentsActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(EquipmentsActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        Button locBtn = findViewById(R.id.locationBtn);
        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocationBookingActivity.class);
                startActivity(intent);
            }
        });

        Button eqBtn = findViewById(R.id.equipmentBtn);
        eqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EquipmentsActivity.class);
                startActivity(intent);
            }
        });

        List<Equipment> equipments = EquipmentService.getInstance().getEquipments();

        EquipmentsAdapter adapter = new EquipmentsAdapter(this, equipments);
        final ListView listView = findViewById(R.id.equipmentList);
        listView.setAdapter(adapter);
    }
}
