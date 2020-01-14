package com.example.sporteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.craftman.cardform.CardForm;


public class PaymentActivity extends AppCompatActivity {

    private CardForm cardform;
    private Button paymentButton;
    private EditText cardNameInput, cardNumberInput, expiryDate, cvv;
    boolean conditionOne = false, conditionTwo = false, conditionThree = false, conditionFour = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardform = findViewById(R.id.cardForm);

        paymentButton = findViewById(R.id.btn_pay);

        TextView paymentPrice = findViewById(R.id.payment_amount);
        TextView cardName = findViewById(R.id.card_preview_name);
        TextView cardPreviewExpiry = findViewById(R.id.card_preview_expiry);
        TextView paymentAmount = findViewById(R.id.payment_amount_holder);

        cardName.setText("Numele cardului");
        cardPreviewExpiry.setText("Data expirării");
        paymentAmount.setText("Suma de plătit");

        cardNameInput = findViewById(R.id.card_name);
        cardNumberInput = findViewById(R.id.card_number);
        expiryDate = findViewById(R.id.expiry_date);
        cvv = findViewById(R.id.cvc);

        cardNameInput.setHint("Numele cardului");
        cardNumberInput.setHint("Numărul cardului");
        expiryDate.setHint("Data expirării");

        paymentButton.setText("Plătește");

        if(getCallingActivity()!=null) {
            String price;

            if (getCallingActivity().getClassName().equals("com.example.sporteam.ViewEventsActivity")) {
                price = getIntent().getStringExtra("price");
            } else {
                price = getIntent().getStringExtra("selectedEqPrice");
            }
            paymentPrice.setText(price);
        }

        cardNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(cardNameInput.getText().toString().length() < 5){
                        Toast.makeText(getApplication(), "Numele trebuie să aiba cel puțin 5 caractere!", Toast.LENGTH_SHORT).show();
                        conditionOne = false;
                    }else{
                        conditionOne = true;
                    }
                }
            }
        });

        cardNumberInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(cardNumberInput.getText().toString().length()!= 19){
                        Toast.makeText(getApplication(), "Numărul cardului este invalid!", Toast.LENGTH_SHORT).show();
                        conditionTwo = false;
                    }else{
                        conditionTwo = true;
                    }
                }
            }
        });

        expiryDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(expiryDate.getText().toString().length()!=5){
                        Toast.makeText(getApplication(), "Data expirării trebuie să aibă formatul LL/AA!", Toast.LENGTH_SHORT).show();
                        conditionThree = false;
                    }else{
                        conditionThree = true;
                    }
                }
            }
        });

        cvv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(cvv.getText().toString().length()!=3){
                        Toast.makeText(getApplication(), "Codul CVV trebuie să fie compus din 3 cifre!", Toast.LENGTH_SHORT).show();
                        conditionFour = false;
                    }else{
                        conditionFour = true;
                    }
                }
            }
        });


        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNameInput.clearFocus();
                cardNumberInput.clearFocus();
                expiryDate.clearFocus();
                cvv.clearFocus();
                if (conditionOne && conditionTwo && conditionThree && conditionFour) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PaymentActivity.this);
                    alertBuilder.setTitle("Confirmă înaintă de plată");
                    alertBuilder.setMessage("Numele cardului: " + cardNameInput.getText().toString() + "\n" +
                            "Numărul cardului: " + cardNumberInput.getText().toString() + "\n" +
                            "Data expirării: " + expiryDate.getText().toString() + "\n" +
                            "CVV: " + cvv.getText().toString());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(PaymentActivity.this, "Plată efectuată cu succes!", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Anulează", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }else{
                    Toast.makeText(getApplication(), "Datele sunt incorecte! Vă rugăm verificați încă o dată!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}