package com.example.cs2340ateam34;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent
                Intent toCreateAccount = new Intent(SplashScreen.this, WelcomeView.class);

                // on below line we are
                // starting a new activity.
                startActivity(toCreateAccount);

                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 1000);
    }
}
