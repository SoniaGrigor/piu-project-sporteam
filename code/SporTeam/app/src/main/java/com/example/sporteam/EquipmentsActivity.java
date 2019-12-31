package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
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

        List<Equipment> equipments = EquipmentService.getInstance().getEquipments();

        EquipmentsAdapter adapter = new EquipmentsAdapter(this, equipments);
        final ListView listView = findViewById(R.id.equipmentList);
        listView.setAdapter(adapter);
    }
}
