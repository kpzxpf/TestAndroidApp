package com.example.test;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeliverySuccessful extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delivery_successful);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView hideTopText = findViewById(R.id.transaction);
        TextView hideBotText = findViewById(R.id.your_rider_);
        ImageView imageView = findViewById(R.id.imageView);
        RelativeLayout relativeLayout = findViewById(R.id.button_done);

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
        int frameCount = animationDrawable.getNumberOfFrames();
        int frameDuration = 0;

        for (int i = 0; i < frameCount; i++) {
            frameDuration += animationDrawable.getDuration(i);
        }

        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideTopText.setVisibility(View.VISIBLE);
                hideBotText.setVisibility(View.VISIBLE);
            }
        }, frameDuration);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliverySuccessful.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}