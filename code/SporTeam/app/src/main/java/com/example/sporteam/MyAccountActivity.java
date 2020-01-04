package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    }

    public void onSynchronisedStepsClicked(View view){

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
                textViewInfo.setText("14311 pasi");
                progressBar.setVisibility(View.GONE);

            }
        }.start();
    }

    public void onSynchronisedTemperatureClicked(View view){

        new CountDownTimer(1000, 1000) {
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
                textViewInfo.setText("36.5 C");
                progressBar.setVisibility(View.GONE);

            }
        }.start();
    }

    public void onSynchronisedPulseClicked(View view){

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
}
