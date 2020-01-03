package com.example.sporteam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sporteam.model.User;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


public class RegisterSecondPageActivity extends AppCompatActivity {

    private EditText sex, age;

    private User user;

    CharSequence[] sports = {"Fotbal", "Baschet", "Înot", "Tenis", "Alergare", "Handbal", "Box", "Biliard"};

    boolean[] selectedSports = {false, false, false, false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_second);

        Intent previousRegistrationPartIntent = getIntent();
        String partialUserJson = previousRegistrationPartIntent.getStringExtra("user");
        user = new Gson().fromJson(partialUserJson, User.class);

        sex = (EditText) findViewById(R.id.registerSex);
        age = (EditText) findViewById(R.id.registerAge);

        final TextView selectedSportsText = (TextView) findViewById(R.id.selectedSports);
        Button sportsButton = (Button) findViewById(R.id.selectSports);

        Button continueButton = (Button) findViewById(R.id.continueRegisterTwo);
        Button finishButton = (Button) findViewById(R.id.finishRegisterTwo);
        selectedSportsText.setText(sportsToString());
        sportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterSecondPageActivity.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Alege sporturile preferate");
                alertDialogBuilder.setMultiChoiceItems(sports, selectedSports, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selectedSports[which] = isChecked;
                    }
                });
                alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedSportsText.setText(sportsToString());
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterSecondPageActivity.this, RegisterThirdPageActivity.class);

                user.setSex(sex.getText().toString());
                user.setAge(Integer.parseInt(age.getText().toString()));

                List<String> sportsToBeAdded = new ArrayList<>();
                for(int i = 0; i < sports.length; i++){
                    if(selectedSports[i]){
                        sportsToBeAdded.add((String) sports[i]);
                    }
                }

                user.setSports(sportsToBeAdded);

                String userJson = (new Gson().toJson(user));
                intent.putExtra("user", userJson);
                startActivity(intent);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterSecondPageActivity.this, MyAccountActivity.class));
            }
        });
    }

    private String sportsToString(){
        StringBuilder text= new StringBuilder();
        for(int i = 0; i <selectedSports.length; i++){
            if(selectedSports[i]){
                text.append(sports[i]).append(" ");
            }
        }

        return text.toString().trim();
    }
}
