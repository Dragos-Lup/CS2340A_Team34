package com.example.cs2340ateam34;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LogInView extends AppCompatActivity {

    //should have an option for google authentication with firebase
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
    }

}
