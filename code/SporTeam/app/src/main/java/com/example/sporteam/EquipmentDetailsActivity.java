package com.example.sporteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sporteam.model.Equipment;
import com.example.sporteam.service.EquipmentService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

public class EquipmentDetailsActivity extends AppCompatActivity {

    private int selectedQuantity;

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
        final TextView itemPrice = findViewById(R.id.itemPrice);
        Button confirm = findViewById(R.id.buyItem);
        final NumberPicker np = findViewById(R.id.number_picker);

        String result = String.format("%.2f", equipment.getPrice()) + " RON";

        itemName.setText(equipment.getName());
        itemImage.setImageResource(equipment.getImage());
        itemDescription.setText(equipment.getDescription());
        itemPrice.setText(result);

        np.setMin(1);
        np.setMax(EquipmentService.getInstance().checkStock(equipment));
        np.setUnit(1);
        np.setValue(1);

        np.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                selectedQuantity = value;
                double price = value * equipment.getPrice();
                String result = String.format("%.2f", price) + " RON";
                np.setMin(1);
                np.setMax(EquipmentService.getInstance().checkStock(equipment) - value);
                np.setUnit(1);
                itemPrice.setText(result);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PaymentActivity.class);
                intent.putExtra("selectedEq", equipment);
                intent.putExtra("selectedQty", selectedQuantity);
                startActivity(intent);
            }
        });
    }
}
