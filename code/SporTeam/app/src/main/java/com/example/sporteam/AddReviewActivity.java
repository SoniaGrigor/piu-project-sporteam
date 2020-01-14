package com.example.sporteam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sporteam.model.Location;
import com.example.sporteam.service.LocationService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class AddReviewActivity extends AppCompatActivity {

    private double stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(AddReviewActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(AddReviewActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(AddReviewActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(AddReviewActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(AddReviewActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
        EditText review = findViewById(R.id.edit_text);
        Button sendReview = findViewById(R.id.sendReview);

        rating.setNumStars(5);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stars = ratingBar.getRating();
            }
        });

        Intent intent = getIntent();
        Location location = (Location) intent.getSerializableExtra("locationForReview");
        LocationService.getInstance().addLocationReview(location, review.getText().toString());
        LocationService.getInstance().addLocationRating(location, stars);

        DecimalFormat df = new DecimalFormat("#.#");
        stars = Double.valueOf(df.format(stars));

        sendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String newR = stars + "/5";
                intent.putExtra("newRating", newR);
                setResult(Activity.RESULT_OK, intent);
                Toast.makeText(getApplicationContext(), "Recenzia a fost adăugată cu succes.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
