package com.example.cs2340ateam34;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
public class CreateAccountView extends AppCompatActivity {
    private EditText username;
    private EditText password;
    public String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(v -> {
            DatabaseReference myRef = database.getReference(username.getText().toString());

        });

    }
    public void databaseFunction() {
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        String username = "dragos";
        DatabaseReference myRef = data.getReference(username);
        answer = myRef.toString();
    }
}
