package com.example.cs2340ateam34;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        Button toLogInButton = findViewById(R.id.Log_In_Button);
        Button toCreateAccountButton = findViewById(R.id.Create_Account_Button);

        toLogInButton.setOnClickListener(v -> {
            Intent toLogIn = new Intent(WelcomeView.this, LogInView.class);
            startActivity(toLogIn);
        });
        toCreateAccountButton.setOnClickListener(v -> {
            Intent toCreateAccount = new Intent(WelcomeView.this, CreateAccountView.class);
            startActivity(toCreateAccount);
        });
    }
}