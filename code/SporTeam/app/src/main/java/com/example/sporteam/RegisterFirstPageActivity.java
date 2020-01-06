package com.example.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sporteam.model.User;
import com.example.sporteam.service.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;


public class RegisterFirstPageActivity extends AppCompatActivity {

    private EditText name, username, email, password, confirmedPassword;
    private UserService userService;
    private boolean conditionOne = false, conditionTwo = false, conditionThree = false, conditionFour = false, conditionFive = false;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_first);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        userService = UserService.getInstance();

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
                if (!hasFocus) {
                    if (name.getText().toString().equals("")) {
                        Toast.makeText(getApplication(), "Numele trebuie completat!", Toast.LENGTH_SHORT).show();
                        conditionOne = false;
                    } else {
                        conditionOne = true;
                    }
                }
            }
        });

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean noDuplicate = true;
                    for (User user : userService.getUsers()) {
                        if (user.getUsername().equals(username.getText().toString())) {
                            Toast.makeText(getApplication(), "Acest nume de utilizator este deja inregistrat!", Toast.LENGTH_SHORT).show();
                            noDuplicate = false;
                            break;
                        }
                    }

                    if (username.getText().toString().equals("")) {
                        Toast.makeText(getApplication(), "Numele de utilizator trebuie completat!", Toast.LENGTH_SHORT).show();
                        conditionTwo = false;
                    } else conditionTwo = noDuplicate;
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean noDuplicate = true;

                    if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                        for (User user : userService.getUsers()) {
                            if (user.getEmail().equals(email.getText().toString())) {
                                Toast.makeText(getApplication(), "Acest email este deja inregistrat!", Toast.LENGTH_SHORT).show();
                                noDuplicate = false;
                                break;
                            }
                        }

                        if (email.getText().toString().equals("")) {
                            Toast.makeText(getApplication(), "Email-ul trebuie completat!", Toast.LENGTH_SHORT).show();
                            conditionThree = false;
                        } else conditionThree = noDuplicate;
                    } else {
                        conditionThree = false;
                        Toast.makeText(getApplication(), "Email-ul trebuie sa fie corect!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (password.getText().toString().equals("")) {
                        Toast.makeText(getApplication(), "Parola trebuie completat!", Toast.LENGTH_SHORT).show();
                        conditionFour = false;
                    } else {
                        conditionFour = true;
                    }
                }
            }
        });

        confirmedPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!confirmedPassword.getText().toString().equals(password.getText().toString())) {
                        Toast.makeText(getApplication(), "Parola si parola confirmata nu se potrivesc!", Toast.LENGTH_SHORT).show();
                        conditionFive = false;
                    } else {
                        conditionFive = true;
                    }
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocuses();
                if (conditionOne && conditionTwo && conditionThree && conditionFour && conditionFive) {
                    Intent intent = new Intent(RegisterFirstPageActivity.this, RegisterSecondPageActivity.class);
                    User user = new User(username.getText().toString(), username.getText().toString(),
                            email.getText().toString(), password.getText().toString());
                    String userJson = (new Gson().toJson(user));
                    intent.putExtra("user", userJson);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Continuarea nu este posibila. Verificati inca o data toate campurile!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocuses();
                if (conditionOne && conditionTwo && conditionThree && conditionFour && conditionFive) {
                    userService.addNewUser(new User(username.getText().toString(), username.getText().toString(),
                            email.getText().toString(), password.getText().toString()));
                    User user = new User(username.getText().toString(), username.getText().toString(),
                            email.getText().toString(), password.getText().toString());
                    userService.addNewUser(user);

                    //register in Firebase
                    auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(RegisterFirstPageActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterFirstPageActivity.this, "Profilul nu poate fi creat. Verificati inca o data toate campurile!" + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        startActivity(new Intent(RegisterFirstPageActivity.this, MyAccountActivity.class));
                                        finish();

                                    }
                                }
                            });

                }
            }
        });
    }

    public void clearFocuses() {
        name.clearFocus();
        username.clearFocus();
        email.clearFocus();
        password.clearFocus();
        confirmedPassword.clearFocus();
    }
}
