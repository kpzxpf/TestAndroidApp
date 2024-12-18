package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton buttonWallet = findViewById(R.id.button_wallet);
        ImageButton buttonTrack = findViewById(R.id.button_track);
        ImageButton buttonProfile = findViewById(R.id.button_profile);
        ImageButton buttonNotification = findViewById(R.id.imageView34);

        RelativeLayout sendPackage = findViewById(R.id.customer_ca2);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });

        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Notification.class);
                startActivity(intent);
                finish();
            }
        });

        buttonWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Wallet.class);
                startActivity(intent);
                finish();
            }
        });

        buttonTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TrackingPackage.class);
                startActivity(intent);
                finish();
            }
        });
        sendPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPackage.setBackgroundColor(Color.BLUE);
                TextView textViewTop = findViewById(R.id.send_a_pack);
                textViewTop.setTextColor(Color.WHITE);
                TextView textViewBot = findViewById(R.id.request_for);
                textViewBot.setTextColor(Color.WHITE);

                Intent intent = new Intent(HomeActivity.this, SendPackage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}