package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.sporteam.adapter.EquipmentsAdapter;
import com.example.sporteam.adapter.LocationsAdapter;
import com.example.sporteam.model.Equipment;
import com.example.sporteam.service.EquipmentService;
import com.example.sporteam.service.LocationService;

import java.util.List;

public class EquipmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);

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
