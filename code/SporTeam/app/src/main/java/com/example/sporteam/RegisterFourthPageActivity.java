package com.example.sporteam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sporteam.model.User;
import com.example.sporteam.service.UserService;
import com.google.gson.Gson;
import java.io.IOException;
import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterFourthPageActivity extends AppCompatActivity {

    private User user;
    private UserService userService = UserService.getInstance();

    private CircleImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fourth);

        Intent previousRegistrationPartIntent = getIntent();
        String partialUserJson = previousRegistrationPartIntent.getStringExtra("user");
        user = new Gson().fromJson(partialUserJson, User.class);

        Button finishButton = findViewById(R.id.finishRegisterFour);

        profileImage = findViewById(R.id.profile_image);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Profile Picture"), PICK_IMAGE);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userService.addNewUser(user);
                startActivity(new Intent(RegisterFourthPageActivity.this, MyAccountActivity.class));
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
                user.setProfileImage(profileImage);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
