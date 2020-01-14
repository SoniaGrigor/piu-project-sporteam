package com.example.sporteam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sporteam.model.User;
import com.example.sporteam.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;


public class ViewAndEditProfileActivity extends AppCompatActivity {

    private CircleImageView profileImage;
    private EditText username, name, email, age, barriers, information;
    private RadioGroup sexRadioGroup;
    private RadioButton sexRadioButton;
    private Button saveButton, sportsButton;
    private UserService userService = UserService.getInstance();
    private User currentUser;

    CharSequence[] sports = {"Fotbal", "Baschet", "Înot", "Tenis", "Ping-Pong", "Alergare", "Handbal", "Box", "Biliard"};
    boolean[] selectedSports = {false, false, false, false, false, false, false, false, false};
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    boolean conditionOne = true, conditionTwo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_edit_profile);

        username = findViewById(R.id.editUsername);
        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        age = findViewById(R.id.editAge);
        barriers = findViewById(R.id.editBarriers);
        information = findViewById(R.id.editInformation);
        profileImage = findViewById(R.id.viewProfileImage);
        sexRadioGroup = findViewById(R.id.editSex);
        saveButton = findViewById(R.id.saveProfileButton);
        sportsButton = findViewById(R.id.editSports);

        List<User> users = userService.getUsers();

        for(User user: users){
            if(user.getUsername().equals(username.getText().toString())){
                username.setText(user.getUsername());
                name.setText(user.getName());
                email.setText(user.getEmail());

                if(user.getAge()>0) {
                    age.setText(user.getAge() + "");
                }else{
                    age.setText("");
                }

                List<String> userSports = user.getSports();

                if(userSports!=null) {
                    for (int i = 0; i < userSports.size(); i++) {
                        if (userSports.contains(sports[i].toString())) {
                            selectedSports[i] = true;
                        }
                    }
                }

                String userSex = user.getSex();
                if(userSex.equals("Masculin")){
                    sexRadioGroup.check(R.id.editSexM);
                }else{
                    sexRadioGroup.check(R.id.editSexF);
                }

                barriers.setText(user.getBarriers());
                information.setText(user.getDescription());
                currentUser = user;

                break;
            }
        }

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Profile Picture"), PICK_IMAGE);
            }
        });

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

        age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(age.getText().toString().matches("\\d+(?:\\.\\d+)?")){
                        conditionTwo = true;
                    }else{
                        Toast.makeText(getApplication(), "Varsta trebuie sa fie un numar intreg!", Toast.LENGTH_SHORT).show();
                        conditionTwo = false;
                    }
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.clearFocus();
                age.clearFocus();
                if(conditionOne && conditionTwo){

                    int radioId = sexRadioGroup.getCheckedRadioButtonId();
                    sexRadioButton = findViewById(radioId);

                    currentUser.setName(name.getText().toString());
                    currentUser.setUsername(username.getText().toString());
                    currentUser.setEmail(email.getText().toString());
                    currentUser.setSex(sexRadioButton.getText().toString());
                    currentUser.setAge(Integer.parseInt(age.getText().toString()));
                    currentUser.setBarriers(barriers.getText().toString());
                    currentUser.setDescription(information.getText().toString());
                    currentUser.setProfileImage(profileImage);

                    List<String> newSports = new ArrayList<String>();
                    for(int i=0; i<sports.length; i++){
                        if(selectedSports[i]){
                            newSports.add(sports[i].toString());
                        }
                    }

                    currentUser.setSports(newSports);
                }else{
                    Toast.makeText(getApplication(), "Profilul nu poate fi editat. Verificați încă o dată toate câmpurile!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewAndEditProfileActivity.this);
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
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
