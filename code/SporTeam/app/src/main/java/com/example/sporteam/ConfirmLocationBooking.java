package com.example.sporteam;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sporteam.model.Location;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class ConfirmLocationBooking {

    private EditText startTime;
    private EditText endTime;
    private Button confirmBtn;
    private EditText bDate;

    private int dateYear, dateMonth, dateDay;
    private Calendar dateCalendar;

    private int startTimeHour, startTimeMinute, endTimeHour, endTimeMinute;
    private Calendar startTimeCalendar;
    private Calendar endTimeCalendar;

    private View v;
    private PopupWindow popupWindow1;

    private Context context1;

    public void showPopupWindow(final View view, final Location location, Context context){
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.confirm_booking, null);

        v = view;
        context1 = context;

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 300);

        popupWindow1 = popupWindow;

        startTime = popupView.findViewById(R.id.popupStart);
        endTime = popupView.findViewById(R.id.popupEnd);
        confirmBtn = popupView.findViewById(R.id.popupConfirmBtn);
        bDate = popupView.findViewById(R.id.popupDate);

        dateCalendar = Calendar.getInstance();
        startTimeCalendar = Calendar.getInstance();
        endTimeCalendar = Calendar.getInstance();

        dateYear = dateCalendar.get(Calendar.YEAR);
        dateMonth = dateCalendar.get(Calendar.MONTH);
        dateDay = dateCalendar.get(Calendar.MINUTE);

        startTimeHour = startTimeCalendar.get(Calendar.HOUR);
        startTimeMinute = startTimeCalendar.get(Calendar.MINUTE);

        endTimeHour = endTimeCalendar.get(Calendar.HOUR);
        endTimeMinute = endTimeCalendar.get(Calendar.MINUTE);

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDatePicker(location);
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimePicker(1, location);
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimePicker(2, location);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(location);
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void handleDatePicker(final Location location){

        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                dateCalendar.set(Calendar.YEAR, year);
                dateCalendar.set(Calendar.MONTH, month);
                dateCalendar.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", dateCalendar).toString();

                bDate.setText(dateText);

                if(LocalDate.of(year, month + 1, date).isBefore(location.getAvailableFrom()) || LocalDate.of(year, month + 1, date).isAfter(location.getAvailableTo())){
                    Toast.makeText(v.getContext(), "Locația nu este disponibilă în această zi (" + location.getAvailableFrom().toString() + " - " + location.getAvailableTo() + ").", Toast.LENGTH_LONG).show();
                    bDate.setText("");
                    return;
                }

                dateYear = year;
                dateMonth = month;
                dateDay = date;
            }
        }, dateYear, dateMonth, dateDay);

        datePickerDialog.show();
    }

    private void handleTimePicker(int id, final Location location) {

        boolean is24HourFormat = DateFormat.is24HourFormat(v.getContext());

        if(id == 1){    //start time
            TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    startTimeCalendar.set(Calendar.HOUR, hour);
                    startTimeCalendar.set(Calendar.MINUTE, minute);
                    String dateText;
                    if(startTimeCalendar.get(Calendar.AM_PM) == Calendar.PM){
                        dateText = DateFormat.format("hh:mm", startTimeCalendar).toString() + " PM";
                    }else{
                        dateText = DateFormat.format("hh:mm", startTimeCalendar).toString() + " AM";
                    }

                    startTime.setText(dateText);

                    if(LocalTime.of(hour, minute).isBefore(location.getStartTime())){
                        Toast.makeText(v.getContext(), "Locația nu este disponibilă începând cu această oră!", Toast.LENGTH_SHORT).show();
                        startTime.setText("");
                        return;
                    }

                    startTimeHour = hour;
                    startTimeMinute = minute;
                }
            }, startTimeHour, startTimeMinute, is24HourFormat);

            timePickerDialog.show();
        }
        else{   //end time
            TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    endTimeCalendar.set(Calendar.HOUR, hour);
                    endTimeCalendar.set(Calendar.MINUTE, minute);
                    String dateText;
                    if(endTimeCalendar.get(Calendar.AM_PM) == Calendar.PM){
                        dateText = DateFormat.format("hh:mm", endTimeCalendar).toString() + " PM";
                    }else{
                        dateText = DateFormat.format("hh:mm", endTimeCalendar).toString() + " AM";
                    }

                    endTime.setText(dateText);

                    if(LocalTime.of(hour, minute).isAfter(location.getEndTime())){
                        Toast.makeText(v.getContext(), "Locația nu este disponibilă până la această oră!", Toast.LENGTH_SHORT).show();
                        endTime.setText("");
                        return;
                    }

                    endTimeHour = hour;
                    endTimeMinute = minute;
                }
            }, endTimeHour, endTimeMinute, is24HourFormat);

            timePickerDialog.show();
        }
    }

    private void validateData(Location location){

        if(bDate.getText().toString().isEmpty() || startTime.getText().toString().isEmpty() || endTime.getText().toString().isEmpty()){
            Toast.makeText(v.getContext(), "Introduceți datele corespunzătoare!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            LocalTime chosenStart = LocalTime.of(startTimeHour, startTimeMinute);
            LocalTime chosenEnd = LocalTime.of(endTimeHour, endTimeMinute);

            if(chosenStart.isAfter(chosenEnd)){
                Toast.makeText(v.getContext(), "Intervalul orar introdus nu este valid!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Toast.makeText(v.getContext(), "Operația a fost executată cu succes!", Toast.LENGTH_SHORT).show();
        popupWindow1.dismiss();
        ((Activity)context1).finish();
    }

}
