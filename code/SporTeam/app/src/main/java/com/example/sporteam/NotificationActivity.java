package com.example.sporteam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
