package com.example.sporteam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(MyAccountActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(MyAccountActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(MyAccountActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(MyAccountActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
    }

    public void onSynchronisedStepsClicked(View view) {

        new CountDownTimer(1000, 1000) {
            ProgressBar progressBar = findViewById(R.id.progressBarPasi);
            TextView textViewInfo = findViewById(R.id.textView_info_pasi);

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setVisibility(View.VISIBLE);
                textViewInfo.setVisibility(View.GONE);
            }

            @Override
            public void onFinish() {
                textViewInfo.setVisibility(View.VISIBLE);
                textViewInfo.setText("14311 pași");
                progressBar.setVisibility(View.GONE);

            }
        }.start();
    }

    public void onSynchronisedTemperatureClicked(View view) {

        new CountDownTimer(1000, 1000) {
            ProgressBar progressBar = findViewById(R.id.progressBarTemperatura);
            TextView textViewInfo = findViewById(R.id.textView_info_temperatura);

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                new AlertDialog.Builder(MyAccountActivity.this)
                        .setTitle("     Conexiune bluetooth")
                        .setMessage("Conexiunea bluetooth este dezactivată.\nActivați bluetooth-ul.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new CountDownTimer(7000, 1000) {
                                    ProgressBar progressBar = findViewById(R.id.progressBarTemperatura);
                                    TextView textViewInfo = findViewById(R.id.textView_info_temperatura);

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        textViewInfo.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onFinish() {
                                        textViewInfo.setVisibility(View.VISIBLE);
                                        textViewInfo.setText("36.5 \u2103");
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }.start();

                            }
                        })
                        .setIcon(R.drawable.ic_bluetooth_disabled_black_18dp)
                        .create()
                        .show();
            }
        }.start();

    }

    public void onSynchronisedPulseClicked(View view) {

        new CountDownTimer(1000, 1000) {
            ProgressBar progressBar = findViewById(R.id.progressBarPuls);
            TextView textViewInfo = findViewById(R.id.textView_info_puls);

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setVisibility(View.VISIBLE);
                textViewInfo.setVisibility(View.GONE);
            }

            @Override
            public void onFinish() {
                textViewInfo.setVisibility(View.VISIBLE);
                textViewInfo.setText("121 bpm");
                progressBar.setVisibility(View.GONE);

            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void navigateToEditAccount(View view){
        startActivity(new Intent(MyAccountActivity.this, ViewAndEditProfileActivity.class));

    }
}
