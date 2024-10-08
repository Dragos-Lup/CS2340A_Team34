package com.example.cs2340ateam34;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInView extends AppCompatActivity {

    //should have an option for google authentication with firebase
    private EditText username;
    private EditText password;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        Button goBackButton = findViewById(R.id.go_back);
        TextView t = findViewById(R.id.invalidCredentialsText);
        goBackButton.setOnClickListener(v -> {
            Intent toCreateAccount = new Intent(LogInView.this, WelcomeView.class);
            startActivity(toCreateAccount);
        });
        Button loginButton = findViewById(R.id.loginButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        loginButton.setOnClickListener(v -> {
            String uname = username.getText().toString();
            String upass = password.getText().toString();

            //There should be code here that checks the database to see if uname
            //is already in the database

            mDatabase.child("users").child(uname).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if (!task.isSuccessful()) {
                            t.setText("Username and Password incorrect! Try creating account?");
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            String x = String.valueOf(task.getResult().getValue());

                            if (x.equals(upass)) {
                                logincode(uname);
                            } else {
                                TextView t = findViewById(R.id.invalidCredentialsText);
                                t.setText("Username and Password incorrect! Try creating account?");
                            }

                        }
                    }
                });
        });
    }
    protected void logincode(String uname) {
        Log.d("logincode", "made it to login");
        Intent toMainActivity = new Intent(LogInView.this, MainActivity.class);
        toMainActivity.putExtra("uname", uname);
        startActivity(toMainActivity);
        TextView t = findViewById(R.id.invalidCredentialsText);
        t.setText("Success");

    }

}
