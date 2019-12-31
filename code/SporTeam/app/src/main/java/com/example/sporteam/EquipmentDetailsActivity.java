package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sporteam.model.Equipment;

public class EquipmentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);

        Intent intent = getIntent();

        final Equipment equipment = (Equipment) intent.getSerializableExtra("equipment");

    }
}
