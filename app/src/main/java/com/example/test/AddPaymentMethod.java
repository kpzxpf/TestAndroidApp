package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPaymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RadioButton radioButtonPayWithWallet = findViewById(R.id.radioButton);
        RadioButton radioButtonCard = findViewById(R.id.radioButton2);
        RadioButton radioButtonCardFirst = findViewById(R.id.radioButton3);
        RadioButton radioButtonCardSecond = findViewById(R.id.radioButton4);

        RelativeLayout creditCardFirst = findViewById(R.id.credit_card);
        RelativeLayout creditCardSecond = findViewById(R.id.credit_card2);


        radioButtonPayWithWallet.setOnClickListener(v -> {
            if (radioButtonPayWithWallet.isChecked()) {
                radioButtonCard.setChecked(false);
            }
            creditCardFirst.setVisibility(View.GONE);
            creditCardSecond.setVisibility(View.GONE);
        });

        radioButtonCard.setOnClickListener(v -> {
            if (radioButtonCard.isChecked()) {
                radioButtonPayWithWallet.setChecked(false);
            }
            creditCardFirst.setVisibility(View.VISIBLE);
            creditCardSecond.setVisibility(View.VISIBLE);
        });

        radioButtonCardFirst.setOnClickListener(v -> {
            if (radioButtonCardFirst.isChecked()) {
                radioButtonCardSecond.setChecked(false);
            }
        });

        radioButtonCardSecond.setOnClickListener(v -> {
            if (radioButtonCardSecond.isChecked()) {
                radioButtonCardFirst.setChecked(false);
            }
        });
    }
}