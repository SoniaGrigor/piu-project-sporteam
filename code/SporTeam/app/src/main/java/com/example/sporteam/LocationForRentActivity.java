package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sporteam.adapter.LocationsAdapter;
import com.example.sporteam.model.Location;
import com.example.sporteam.service.LocationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class LocationForRentActivity extends AppCompatActivity {

    private int year, month, day;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String availableFromDate;
    private String availableToDate;

    private EditText locationName;
    private EditText locationAddress;
    private EditText ownerName;
    private EditText ownerPhone;
    private EditText availableFrom;
    private EditText availableTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_for_rent);

        final Calendar calendar = Calendar.getInstance();

        locationName = findViewById(R.id.locRentNameVal);
        locationAddress = findViewById(R.id.locRentAddressVal);
        ownerName = findViewById(R.id.locRentOwnerNameVal);
        ownerPhone = findViewById(R.id.locRentOwnerPhoneVal);
        availableFrom = findViewById(R.id.locRentAvFromVal);
        availableTo = findViewById(R.id.locRentAvToVal);

//        availableFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                year = calendar.get(Calendar.YEAR);
//                month = calendar.get(Calendar.MONTH);
//                day = calendar.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(LocationForRentActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
//
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//            }
//        });
//
//        availableTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                year = calendar.get(Calendar.YEAR);
//                month = calendar.get(Calendar.MONTH);
//                day = calendar.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(LocationForRentActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
//
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//            }
//        });
//
//        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month+1;
//
//                availableFromDate = day + "/" + month + "/" + year;
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//
//                //convert String to LocalDate
//                LocalDate localDate = LocalDate.parse(availableFromDate, formatter);
//
//                System.out.println(availableFromDate + " " + localDate.toString());
//            }
//        };

        final String[] date = new String[2];

        View.OnFocusChangeListener showDatePicker = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final View vv = v;

                DatePickerDialog datePickerDialog = new DatePickerDialog(LocationForRentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (vv.getId() == R.id.locRentAvFromVal) { //id of your StartDate button) {
                            date[0] = year + " " + monthOfYear + " " + dayOfMonth;
                            System.out.println(date[0]);
                        } else { //EndDate button was clicked {
                            date[1] = year + " " + month + " " + day;
                            System.out.println(date[1]);
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        };

        availableFrom.setOnFocusChangeListener(showDatePicker);
        availableTo.setOnFocusChangeListener(showDatePicker);

    }

    public void confirmRent(View view){

        if(locationName.getText().toString().equals("")){
            System.out.println("svsvsvhjshvs" + locationName.getText().toString());
            Toast.makeText(getApplicationContext(), "Introduceți numele locației!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(locationAddress.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Introduceti adresa locației!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ownerName.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Introduceți numele proprietarului!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ownerPhone.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Introduceți numărul de telefon al proprietarului!", Toast.LENGTH_SHORT).show();
            return;
        }

//        if(availableFrom.getText().toString().isEmpty()){
//            Toast.makeText(getApplicationContext(), "Introduceți datele referitoare la durata disponibilității!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if(availableTo.getText().toString().isEmpty()){
//            Toast.makeText(getApplicationContext(), "Introduceți datele referitoare la durata disponibilității!", Toast.LENGTH_SHORT).show();
//            return;
//        }

        Location location = new Location();
        location.setImage(R.drawable.location);
        location.setName(locationName.getText().toString());
        location.setAddress(locationAddress.getText().toString());
        location.setOwnerName(ownerName.getText().toString());
        location.setPhoneNumber(ownerPhone.getText().toString());
        location.setAvailableFrom(LocalDateTime.now());
        location.setAvailableTo(LocalDateTime.now());

        Intent intent = new Intent();
        intent.putExtra("rentLocation", location);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
