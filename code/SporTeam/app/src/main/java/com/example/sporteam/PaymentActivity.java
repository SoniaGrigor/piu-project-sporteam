package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;


public class PaymentActivity extends AppCompatActivity {

    private EditText cardOwner, cardNumber, cardCvv, cardExpiration;
    private int dateYear, dateMonth, dateDay;
    private Calendar expirationDateCalendar;
    private Button confirmButton;

    boolean conditionOne = false, conditionTwo = false, conditionThree = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardOwner = findViewById(R.id.cardOwner);
        cardNumber = findViewById(R.id.cardNumber);
        cardCvv = findViewById(R.id.cvv);
        cardExpiration = findViewById(R.id.cardExpiration);
        confirmButton = findViewById(R.id.paymentConfirmBtn);

        expirationDateCalendar = Calendar.getInstance();
        dateYear = expirationDateCalendar.get(Calendar.YEAR);
        dateMonth = expirationDateCalendar.get(Calendar.MONTH);
        dateDay = expirationDateCalendar.get(Calendar.DATE);
        cardExpiration.setText(DateFormat.format("EEEE, MMM d, yyyy", expirationDateCalendar).toString());

        cardOwner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(cardOwner.getText().length() <5){
                        conditionOne = false;
                        Toast.makeText(PaymentActivity.this, "Numele de titular trebuie să conțină cel puțin 5 caractere!", Toast.LENGTH_SHORT).show();
                    }else{
                        conditionOne = true;
                    }
                }
            }
        });

        cardNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(cardNumber.getText().length() != 16){
                        conditionTwo = false;
                        Toast.makeText(PaymentActivity.this, "Numărul de card este invalid! Trebuie să aibă 16 cifre!", Toast.LENGTH_SHORT).show();
                    }else{
                        conditionTwo = true;
                    }
                }
            }
        });

        cardCvv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(cardCvv.getText().length() != 3){
                        conditionThree = false;
                        Toast.makeText(PaymentActivity.this, "CVV invalid! Trebuie să aibă 3 cifre!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    conditionThree = true;
                }
            }
        });

        cardExpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDatePicker();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardOwner.clearFocus();
                cardNumber.clearFocus();
                cardCvv.clearFocus();

                if(conditionOne && conditionTwo && conditionThree){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(getCallingActivity().getClassName().equals("com.example.sporteam.ViewEventsActivity")) {
                    Toast.makeText(getApplication(), "Plată efectuată cu succes! Te-ai alăturat cu succes la eveniment!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), "Plată efectuată cu succes!", Toast.LENGTH_SHORT).show();
                }
                finish();
                }else{

                    Toast.makeText(PaymentActivity.this, "Plata nu se poate efectua! Verificați încă o dată toate câmpurile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                expirationDateCalendar.set(Calendar.YEAR, year);
                expirationDateCalendar.set(Calendar.MONTH, month);
                expirationDateCalendar.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", expirationDateCalendar).toString();

                cardExpiration.setText(dateText);

                dateYear = year;
                dateMonth = month;
                dateDay = date;
            }
        }, dateYear, dateMonth, dateDay);

        datePickerDialog.show();
    }
}