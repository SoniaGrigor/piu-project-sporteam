package com.example.sporteam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sporteam.model.Event;
import com.example.sporteam.service.EventService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;


public class CreateEventActivity extends Activity {

    private Button sportButton, distributeButton;

    private RadioGroup paymentMethodRadioGroup;
    private RadioButton paymentMethodRadioButton;

    private RadioGroup eventTypeRadioGroup;
    private RadioButton eventTypeRadioButton;

    private EditText eventDate, eventTime, eventLocation, minimumPersonNumber, maximumPersonNumber, eventPrice;

    CharSequence[] sports = {"Fotbal", "Baschet", "Înot", "Tenis", "Ping-Pong", "Alergare", "Handbal", "Box", "Biliard"};
    int selectedItem = 0;

    private int eventYear, eventMonth, eventDay;
    private Calendar eventDateCalendar;

    private int eventHour, eventMinute;
    private Calendar eventTimeCalendar;

    boolean conditionOne = false, conditionTwo = false, conditionThree = false, conditionFour = false;

    private EventService eventService = EventService.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_setting:
                        startActivity(new Intent(CreateEventActivity.this, NotificationActivity.class));
                        break;
                    case R.id.navigation_event:
                        startActivity(new Intent(CreateEventActivity.this, ViewEventsActivity.class));
                        break;
                    case R.id.navigation_location:
                        startActivity(new Intent(CreateEventActivity.this, LocationBookingActivity.class));
                        break;
                    case R.id.navigation_chat:
                        startActivity(new Intent(CreateEventActivity.this, ConversationsActivity.class));
                        break;
                    case R.id.navigation_profile:
                        startActivity(new Intent(CreateEventActivity.this, MyAccountActivity.class));
                        break;
                }
                return false;
            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        sportButton = findViewById(R.id.eventSportButton);
        distributeButton = findViewById(R.id.eventButton);
        paymentMethodRadioGroup = findViewById(R.id.eventPaymentMethodRadioGroup);
        eventTypeRadioGroup = findViewById(R.id.eventTypeRadioGroup);
        eventDate = findViewById(R.id.eventDateInput);
        eventTime = findViewById(R.id.eventTimeInput);
        eventLocation = findViewById(R.id.eventLocationInput);
        minimumPersonNumber = findViewById(R.id.eventMinimumPersonNumberInput);
        maximumPersonNumber = findViewById(R.id.eventMaximumPersonNumberInput);
        eventPrice = findViewById(R.id.eventPriceInput);

        eventDateCalendar = Calendar.getInstance();
        eventYear = eventDateCalendar.get(Calendar.YEAR);
        eventMonth = eventDateCalendar.get(Calendar.MONTH);
        eventDay = eventDateCalendar.get(Calendar.DATE);
        eventDate.setText(DateFormat.format("EEEE, MMM d, yyyy", eventDateCalendar).toString());

        eventTimeCalendar = Calendar.getInstance();
        eventHour = eventTimeCalendar.get(Calendar.HOUR);
        eventMinute = eventTimeCalendar.get(Calendar.MINUTE);

        if(eventTimeCalendar.get(Calendar.AM_PM) == Calendar.PM) {
            eventTime.setText(DateFormat.format("hh:mm", eventTimeCalendar).toString() + " PM");
        }else{
            eventTime.setText(DateFormat.format("hh:mm", eventTimeCalendar).toString() + " AM");
        }

        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateEventActivity.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Sportul ales pentru acest eveniment:");
                alertDialogBuilder.setSingleChoiceItems(sports, selectedItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sportButton.setText(sports[which]);
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateEditText();
            }
        });

        eventTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeEditText();
            }
        });

        eventLocation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(eventLocation.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Locația nu poate fi lăsată goală!", Toast.LENGTH_SHORT).show();
                        conditionOne = false;
                    }else{
                        conditionOne = true;
                    }
                }
            }
        });

        minimumPersonNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(minimumPersonNumber.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Numărul minim de persoane trebuie să fie un număr întreg!", Toast.LENGTH_SHORT).show();
                        conditionTwo = false;
                    }else{
                        conditionTwo = true;
                    }
                }
            }
        });

        maximumPersonNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(maximumPersonNumber.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Numărul maxim de persoane trebuie să fie un număr întreg!", Toast.LENGTH_SHORT).show();
                        conditionThree = false;
                    }else{
                        conditionThree = true;
                    }
                }
            }
        });

        eventPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(eventPrice.getText().toString().equals("")){
                        Toast.makeText(getApplication(), "Trebuie introdus un preț pe care participanții trebuie să îl plătească pentru alăturare!", Toast.LENGTH_SHORT).show();
                        conditionFour = false;
                    }else{
                        conditionFour = true;
                    }
                }
            }
        });

        distributeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventDate.clearFocus();
                eventTime.clearFocus();
                eventLocation.clearFocus();
                minimumPersonNumber.clearFocus();
                maximumPersonNumber.clearFocus();

                if(conditionOne && conditionTwo && conditionThree){

                    int minimumNumber =  Integer.parseInt(minimumPersonNumber.getText().toString());
                    int maximumNumber = Integer.parseInt(maximumPersonNumber.getText().toString());

                    if(maximumNumber >= minimumNumber) {

                        String sportName = sportButton.getText().toString();
                        int eventImage = returnImageBySportName(sportName);
                        String date = eventDate.getText().toString();
                        String time = eventTime.getText().toString();
                        String location = eventLocation.getText().toString();
                        String price = eventPrice.getText().toString() + " LEI";

                        int paymentMethodRadioId = paymentMethodRadioGroup.getCheckedRadioButtonId();
                        paymentMethodRadioButton = findViewById(paymentMethodRadioId);
                        String paymentMethod = paymentMethodRadioButton.getText().toString();

                        int eventTypeRadioId = eventTypeRadioGroup.getCheckedRadioButtonId();
                        eventTypeRadioButton = findViewById(eventTypeRadioId);
                        String eventType = eventTypeRadioButton.getText().toString();

                        Event newEvent = new Event(eventImage, sportName, date, time, location, price, minimumNumber, maximumNumber,
                                paymentMethod, eventType);
                        eventService.addNewEvent(newEvent);
                        Toast.makeText(getApplication(), "Evenimentul nou a fost creeat cu succes!", Toast.LENGTH_SHORT).show();
                        finish();

                    }else{
                        Toast.makeText(getApplication(), "Numărul maxim de persoane trebuie să fie egal sau mai mare decât numărul minim!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplication(), "Evenimentul nu poate fi creat! Verificați încă o dată toate câmpurile!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleDateEditText(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                eventDateCalendar.set(Calendar.YEAR, year);
                eventDateCalendar.set(Calendar.MONTH, month);
                eventDateCalendar.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", eventDateCalendar).toString();

                eventDate.setText(dateText);

                eventYear = year;
                eventMonth = month;
                eventDay = date;
            }
        }, eventYear, eventMonth, eventDay);

        datePickerDialog.show();
    }

    private void handleTimeEditText() {

        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i("CreateEventActivity", "onTimeSet: " + hour + minute);
                eventTimeCalendar.set(Calendar.HOUR, hour);
                eventTimeCalendar.set(Calendar.MINUTE, minute);
                String dateText;
                if(eventTimeCalendar.get(Calendar.AM_PM) == Calendar.PM){
                    dateText = DateFormat.format("hh:mm", eventTimeCalendar).toString() + " PM";
                }else{
                    dateText = DateFormat.format("hh:mm", eventTimeCalendar).toString() + " AM";
                }

                eventTime.setText(dateText);
                eventHour = hour;
                eventMinute = minute;
            }
        }, eventHour, eventMinute, is24HourFormat);

        timePickerDialog.show();
    }

    private int returnImageBySportName(String sport){

        switch (sport){
            case "Fotbal" : return R.drawable.football;
            case "Baschet" : return R.drawable.basketball;
            case "Înot" : return R.drawable.swimming;
            case "Tenis" : return R.drawable.tennis;
            case "Ping-Pong" : return R.drawable.pingpong;
            case "Alergare" : return R.drawable.running;
            case "Handbal" : return R.drawable.handball;
            case "Box" : return R.drawable.boxing;
            case "Biliard" : return R.drawable.billiard;
            default : return 0; //it should never come here
        }
    }
}
