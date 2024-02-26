package com.example.cs2340ateam34;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class CreateAccountView extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        createAccountButton.setOnClickListener(v -> {

            String uname = username.getText().toString();
            String upass = password.getText().toString();

            //There should be code here that checks the database to see if uname is already in the database


            if (!uname.isEmpty() && !uname.contains(" ")
                && !upass.isEmpty() && !upass.contains(" ")) {
                mDatabase.child("users").child(uname).setValue(upass);
                logincode();
            } else {
                TextView t = findViewById(R.id.invalidCredentialsText);
                t.setText("Username and Password cannot contain spaces or be null!");
            }

            //TextView t = findViewById(R.id.createAccountTitle);

            //t.setText(mDatabase.child("users").child(username.getText().toString()).get()[0].getValue(password));
            /*
            mDatabase.child("users").child(uname).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        x = String.valueOf(task.getResult().getValue());
                    }
                }
            });

            t.setText(x);
            */
        });


    }
    protected void logincode() {
        Intent toCreateAccount = new Intent(CreateAccountView.this, MainActivity.class);
        startActivity(toCreateAccount);

    }
}
