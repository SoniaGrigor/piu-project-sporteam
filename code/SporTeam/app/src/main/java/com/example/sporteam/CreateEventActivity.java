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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sporteam.model.Event;
import com.example.sporteam.service.EventService;

import java.util.Calendar;
import java.util.Date;


public class CreateEventActivity extends Activity {

    private Button sportButton, distributeButton;

    private RadioGroup paymentMethodRadioGroup;
    private RadioButton paymentMethodRadioButton;

    private RadioGroup eventTypeRadioGroup;
    private RadioButton eventTypeRadioButton;

    private EditText eventDate, eventTime, eventLocation, minimumPersonNumber, maximumPersonNumber;

    CharSequence[] sports = {"Fotbal", "Baschet", "Înot", "Tenis", "Ping-Pong", "Alergare", "Handbal", "Box", "Biliard"};
    int selectedItem = 0;

    private int eventYear, eventMonth, eventDay;
    private Calendar eventDateCalendar;

    private int eventHour, eventMinute;
    private Calendar eventTimeCalendar;

    boolean conditionOne = false, conditionTwo = false, conditionThree = false;

    private EventService eventService = EventService.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        sportButton = findViewById(R.id.eventSportButton);
        distributeButton = findViewById(R.id.eventButton);
        paymentMethodRadioGroup = findViewById(R.id.eventPaymentMethodRadioGroup);
        eventTypeRadioGroup = findViewById(R.id.eventTypeRadioGroup);
        eventDate = findViewById(R.id.eventDateInput);
        eventTime = findViewById(R.id.eventTimeInput);
        eventLocation = findViewById(R.id.eventLocationInput);
        minimumPersonNumber = findViewById(R.id.eventMinimumPersonNumberInput);
        maximumPersonNumber = findViewById(R.id.eventMaximumPersonNumberInput);

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
                alertDialogBuilder.setTitle("Sportul practicat in eveniment:");
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
                        Toast.makeText(getApplication(), "Locatia nu poate fi lasata goala!", Toast.LENGTH_SHORT).show();
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
                    if(!minimumPersonNumber.getText().toString().matches("\\d+(?:\\.\\d+)?")){
                        Toast.makeText(getApplication(), "Numarul minim de persoane trebuie sa fie un numar intreg!!", Toast.LENGTH_SHORT).show();
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
                    if(!maximumPersonNumber.getText().toString().matches("\\d+(?:\\.\\d+)?")){
                        Toast.makeText(getApplication(), "Numarul maxim de persoane trebuie sa fie un numar intreg!!", Toast.LENGTH_SHORT).show();
                        conditionThree = false;
                    }else{
                        conditionThree = true;
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

                        int paymentMethodRadioId = paymentMethodRadioGroup.getCheckedRadioButtonId();
                        paymentMethodRadioButton = findViewById(paymentMethodRadioId);
                        String paymentMethod = paymentMethodRadioButton.getText().toString();

                        int eventTypeRadioId = eventTypeRadioGroup.getCheckedRadioButtonId();
                        eventTypeRadioButton = findViewById(eventTypeRadioId);
                        String eventType = eventTypeRadioButton.getText().toString();

                        Event newEvent = new Event(eventImage, sportName, date, time, location, minimumNumber, maximumNumber,
                                paymentMethod, eventType);
                        eventService.addNewEvent(newEvent);
                        startActivity(new Intent(CreateEventActivity.this, MyAccountActivity.class));

                    }else{
                        Toast.makeText(getApplication(), "Numarul maxim de persoane trebuie sa fie egal sau mai mare decat numarul minim!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplication(), "Evenimentul nu poate fi creat! Verificati inca o data toate campurile!", Toast.LENGTH_SHORT).show();
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
