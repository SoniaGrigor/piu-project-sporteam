package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sporteam.model.User;
import com.google.gson.Gson;

public class RegisterThirdPageActivity extends AppCompatActivity {

    private EditText barriers, informations;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_third);

        Intent previousRegistrationPartIntent = getIntent();
        String partialUserJson = previousRegistrationPartIntent.getStringExtra("user");
        user = new Gson().fromJson(partialUserJson, User.class);

        barriers = (EditText) findViewById(R.id.registerBarriers);
        informations = (EditText) findViewById(R.id.registerInformations);
    }
}
