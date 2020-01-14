package com.example.sporteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MonitorizareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitorizare);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(MonitorizareActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(MonitorizareActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(MonitorizareActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(MonitorizareActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(MonitorizareActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    public void confirmBodyInformation(View view) {
        Toast.makeText(getApplicationContext(), "Datele dumneavoastre au fost înregistrate!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MonitorizareActivity.this, ViewEventsActivity.class));
        finish();
    }

    public void navigateToNotificationActivity(View view){
        startActivity(new Intent(MonitorizareActivity.this, NotificationActivity.class));
        finish();
    }
}
