package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sporteam.model.User;
import com.example.sporteam.service.UserService;
import com.google.gson.Gson;


public class RegisterFirstPageActivity extends AppCompatActivity {

    private EditText name, username, email, password, confirmedPassword;
    private UserService userService;
    private boolean conditionOne = false, conditionTwo = false, conditionThree = false, conditionFour = false, conditionFive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_first);

        userService = new UserService();

        name = (EditText) findViewById(R.id.registerName);
        username = (EditText) findViewById(R.id.registerUsername);
        email = (EditText) findViewById(R.id.registerEmail);
        password = (EditText) findViewById(R.id.registerPassword);
        confirmedPassword = (EditText) findViewById(R.id.registerPasswordRepeat);
        Button continueButton = (Button) findViewById(R.id.continueRegisterOne);
        Button finishButton = (Button) findViewById(R.id.finishRegisterOne);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(name.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Name can't be left empty!", Toast.LENGTH_SHORT).show();
                        conditionOne = false;
                    }else{
                        conditionOne = true;
                    }
                }
            }
        });

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    boolean noDuplicate = true;
                    for (User user : userService.getUsers()){
                        if(user.getUsername().equals(username.getText().toString())){
                            Toast.makeText(getApplication(), "This username already exists!", Toast.LENGTH_SHORT).show();
                            noDuplicate = false;
                            break;
                        }
                    }

                     if(username.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Username can't be left empty!", Toast.LENGTH_SHORT).show();
                        conditionTwo = false;
                    }else conditionTwo = noDuplicate;
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    boolean noDuplicate = true;
                    for(User user : userService.getUsers()){
                        if(user.getEmail().equals(email.getText().toString())){
                            Toast.makeText(getApplication(), "This email already exists!", Toast.LENGTH_SHORT).show();
                            noDuplicate = false;
                            break;
                        }
                    }

                    if(email.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Email can't be left empty!", Toast.LENGTH_SHORT).show();
                        conditionThree = false;
                    }else conditionThree = noDuplicate;
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(password.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Password can't be left empty!", Toast.LENGTH_SHORT).show();
                        conditionFour = false;
                    }else{
                        conditionFour = true;
                    }
                }
            }
        });

        confirmedPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!confirmedPassword.getText().toString().equals(password.getText().toString())){
                        Toast.makeText(getApplication(), "Parola si parola confirmata nu se potrivesc!", Toast.LENGTH_SHORT).show();
                        conditionFive = false;
                    }else{
                        conditionFive = true;
                    }
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(conditionOne && conditionTwo && conditionThree && conditionFour && conditionFive){
                    Intent intent = new Intent(RegisterFirstPageActivity.this, RegisterSecondPageActivity.class);
                    User user = new User(username.getText().toString(), username.getText().toString(),
                            email.getText().toString(), password.getText().toString());
                    String userJson = (new Gson().toJson(user));
                    intent.putExtra("user", userJson);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplication(), "Continuarea nu este posibila. Verificati inca o data toate campurile!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conditionOne && conditionTwo && conditionThree && conditionFour && conditionFive){
                    userService.addNewUser(new User(username.getText().toString(), username.getText().toString(),
                            email.getText().toString(), password.getText().toString()));
                    startActivity(new Intent(RegisterFirstPageActivity.this, MyAccountActivity.class));
                }else{
                    Toast.makeText(getApplication(), "Profilul nu poate fi creat. Verificati inca o data toate campurile!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
