package com.example.sporteam;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sporteam.model.Location;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class LocationForRentActivity extends AppCompatActivity {

    private EditText locationName;
    private EditText locationAddress;
    private EditText ownerName;
    private EditText ownerPhone;
    private EditText availableFrom;
    private EditText availableTo;
    private EditText startTime;
    private EditText endTime;

    private int availableFromYear, availableFromMonth, availableFromDay, availableToYear, availableToMonth, availableToDay;
    private Calendar availableFromCalendar;
    private Calendar availableToCalendar;

    private int startTimeHour, startTimeMinute, endTimeHour, endTimeMinute;
    private Calendar startTimeCalendar;
    private Calendar endTimeCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_for_rent);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(LocationForRentActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(LocationForRentActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(LocationForRentActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(LocationForRentActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(LocationForRentActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        locationName = findViewById(R.id.locRentNameVal);
        locationAddress = findViewById(R.id.locRentAddressVal);
        ownerName = findViewById(R.id.locRentOwnerNameVal);
        ownerPhone = findViewById(R.id.locRentOwnerPhoneVal);
        availableFrom = findViewById(R.id.locRentAvFromVal);
        availableTo = findViewById(R.id.locRentAvToVal);
        startTime = findViewById(R.id.startTimeVal);
        endTime = findViewById(R.id.endTimeVal);

        availableFromCalendar = Calendar.getInstance();
        availableToCalendar = Calendar.getInstance();

        availableFromYear = availableFromCalendar.get(Calendar.YEAR);
        availableFromMonth = availableFromCalendar.get(Calendar.MONTH);
        availableFromDay = availableFromCalendar.get(Calendar.DATE);

        availableToYear = availableToCalendar.get(Calendar.YEAR);
        availableToMonth = availableToCalendar.get(Calendar.MONTH);
        availableToDay = availableToCalendar.get(Calendar.DATE);

        startTimeCalendar = Calendar.getInstance();
        endTimeCalendar = Calendar.getInstance();

        startTimeHour = startTimeCalendar.get(Calendar.HOUR);
        startTimeMinute = startTimeCalendar.get(Calendar.MINUTE);

        endTimeHour = endTimeCalendar.get(Calendar.HOUR);
        endTimeMinute = endTimeCalendar.get(Calendar.MINUTE);

        availableFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    handleDatePicker(1);
                }
            }
        });

        availableTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    handleDatePicker(2);
                }
            }
        });

        startTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    handleTimePicker(1);
                }
            }
        });

        endTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    handleTimePicker(2);
                }
            }
        });
    }

    private void handleDatePicker(int id){
        if(id == 1){    //available from date
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int date) {
                    availableFromCalendar.set(Calendar.YEAR, year);
                    availableFromCalendar.set(Calendar.MONTH, month);
                    availableFromCalendar.set(Calendar.DATE, date);
                    String dateText = DateFormat.format("EEEE, MMM d, yyyy", availableFromCalendar).toString();

                    availableFrom.setText(dateText);

                    availableFromYear = year;
                    availableFromMonth = month;
                    availableFromDay = date;
                }
            }, availableFromYear, availableFromMonth, availableFromDay);

            datePickerDialog.show();
        }
        else{   //available to date
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int date) {
                    availableToCalendar.set(Calendar.YEAR, year);
                    availableToCalendar.set(Calendar.MONTH, month);
                    availableToCalendar.set(Calendar.DATE, date);
                    String dateText = DateFormat.format("EEEE, MMM d, yyyy", availableToCalendar).toString();

                    availableTo.setText(dateText);

                    availableToYear = year;
                    availableToMonth = month;
                    availableToDay = date;
                }
            }, availableToYear, availableToMonth, availableToDay);

            datePickerDialog.show();
        }
    }

    private void handleTimePicker(int id) {

        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        if(id == 1){    //start time
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    Log.i("LocationForRentActivity", "onTimeSet: " + hour + minute);
                    startTimeCalendar.set(Calendar.HOUR, hour);
                    startTimeCalendar.set(Calendar.MINUTE, minute);
                    String dateText;
                    if(startTimeCalendar.get(Calendar.AM_PM) == Calendar.PM){
                        dateText = DateFormat.format("hh:mm", startTimeCalendar).toString() + " PM";
                    }else{
                        dateText = DateFormat.format("hh:mm", startTimeCalendar).toString() + " AM";
                    }

                    startTime.setText(dateText);
                    startTimeHour = hour;
                    startTimeMinute = minute;
                }
            }, startTimeHour, startTimeMinute, is24HourFormat);

            timePickerDialog.show();
        }
        else{   //end time
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    Log.i("LocationForRentActivity", "onTimeSet: " + hour + minute);
                    endTimeCalendar.set(Calendar.HOUR, hour);
                    endTimeCalendar.set(Calendar.MINUTE, minute);
                    String dateText;
                    if(endTimeCalendar.get(Calendar.AM_PM) == Calendar.PM){
                        dateText = DateFormat.format("hh:mm", endTimeCalendar).toString() + " PM";
                    }else{
                        dateText = DateFormat.format("hh:mm", endTimeCalendar).toString() + " AM";
                    }

                    endTime.setText(dateText);
                    endTimeHour = hour;
                    endTimeMinute = minute;
                }
            }, endTimeHour, endTimeMinute, is24HourFormat);

            timePickerDialog.show();
        }
    }

    public void confirmRent(View view){

        if(locationName.getText().toString().equals("")){
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

        if(availableFrom.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Introduceți datele referitoare la disponibilitate!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(availableTo.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Introduceți datele referitoare la disponibilitate!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(startTime.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Introduceți datele referitoare la disponibilitate!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(endTime.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Introduceți datele referitoare la disponibilitate!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(LocalDate.of(availableFromYear, availableFromMonth + 1, availableFromDay).isAfter(LocalDate.of(availableToYear, availableToMonth + 1, availableToDay))){
            Toast.makeText(getApplicationContext(), "Data de început trebuie să fie anterioară datei de sfârșit!", Toast.LENGTH_LONG).show();
            return;
        }

        Location location = new Location();
        location.setImage(R.drawable.location);
        location.setName(locationName.getText().toString());
        location.setAddress(locationAddress.getText().toString());
        location.setOwnerName(ownerName.getText().toString());
        location.setOwnerPhoneNumber(ownerPhone.getText().toString());
        location.setAvailableFrom(LocalDate.of(availableFromYear, availableFromMonth + 1, availableFromDay));
        location.setAvailableTo(LocalDate.of(availableToYear, availableToMonth + 1, availableToDay));
        location.setStartTime(LocalTime.of(startTimeHour, startTimeMinute));
        location.setEndTime(LocalTime.of(endTimeHour, endTimeMinute));

        Toast.makeText(getApplicationContext(), "Operația a fost executată cu succes!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("rentLocation", location);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
