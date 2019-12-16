package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class LocationForRentActivity extends AppCompatActivity {

    private int year, month, day;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String availableFromDate;
    private String availableToDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_for_rent);

        final Calendar calendar = Calendar.getInstance();
        System.out.println("sjafhasfhsakhsjaskhsak");

        EditText locationName = findViewById(R.id.locRentOwnerNameVal);
        EditText locationAddress = findViewById(R.id.locRentAddressVal);
        EditText ownerName = findViewById(R.id.locRentOwnerNameVal);
        EditText ownerPhone = findViewById(R.id.locRentOwnerPhoneVal);
        EditText availableFrom = findViewById(R.id.locRentAvFromVal);
        EditText availableTo = findViewById(R.id.locRentAvToVal);

        availableFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LocationForRentActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        availableTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LocationForRentActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;

                availableFromDate = day + "/" + month + "/" + year;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

                //convert String to LocalDate
                LocalDate localDate = LocalDate.parse(availableFromDate, formatter);

                System.out.println(availableFromDate + " " + localDate.toString());
            }
        };
    }

    public void confirmRent(View view){

    }

}
