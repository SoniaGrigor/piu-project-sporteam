package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sporteam.model.User;
import com.example.sporteam.service.UserService;
import com.google.gson.Gson;


public class RegisterThirdPageActivity extends AppCompatActivity {

    private EditText barriers, informations;

    private User user;
    private UserService userService = UserService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_third);

        Intent previousRegistrationPartIntent = getIntent();
        String partialUserJson = previousRegistrationPartIntent.getStringExtra("user");
        user = new Gson().fromJson(partialUserJson, User.class);

        barriers = (EditText) findViewById(R.id.registerBarriers);
        informations = (EditText) findViewById(R.id.registerInformations);

        Button continueButton = (Button) findViewById(R.id.continueRegisterThree);
        Button finishButton = (Button) findViewById(R.id.finishRegisterThree);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterThirdPageActivity.this, RegisterFourthPageActivity.class);

                user.setBarriers(barriers.getText().toString());
                user.setDescription(informations.getText().toString());

                String userJson = (new Gson().toJson(user));
                intent.putExtra("user",userJson);
                startActivity(intent);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setBarriers(barriers.getText().toString());
                user.setDescription(barriers.getText().toString());
                userService.addNewUser(user);
                startActivity(new Intent(RegisterThirdPageActivity.this, MyAccountActivity.class));
            }
        });
    }
}
