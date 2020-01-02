package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sporteam.model.Equipment;

public class EquipmentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);

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
