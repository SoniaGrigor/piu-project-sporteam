package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    }

    public void onSynchronisedStepsClicked(View view){

        Toast toast = Toast.makeText(getApplicationContext(), "Steps", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onSynchronisedTemperatureClicked(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Temperature", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onSynchronisedPulseClicked(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Pulse", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
