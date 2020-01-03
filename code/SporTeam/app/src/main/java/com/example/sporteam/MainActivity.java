package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dl.addDrawerListener(t);
        t.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent h;
                switch(id)
                {
                    case R.id.nav_notification_drawer:
                        h = new Intent(MainActivity.this, NotificationActivity.class);
                        startActivity(h);
                        Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_events_drawer:
                        h = new Intent(MainActivity.this, EventsActivity.class);
                        startActivity(h);
                        Toast.makeText(MainActivity.this, "EventsActivity", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_my_account_drawer:
                        h = new Intent(MainActivity.this, MyAccountActivity.class);
                        startActivity(h);
                        Toast.makeText(MainActivity.this, "MyAccountActivity", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_location_booking_drawer:
                        h = new Intent(MainActivity.this, LocationBookingActivity.class);
                        startActivity(h);
                        Toast.makeText(MainActivity.this, "LocationBookingActivity", Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }


                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}