package com.example.cs2340ateam34;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountView extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        Button goBackButton = findViewById(R.id.go_back);

        goBackButton.setOnClickListener(v -> {
            Intent toCreateAccount = new Intent(CreateAccountView.this, WelcomeView.class);
            startActivity(toCreateAccount);
        });

        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        createAccountButton.setOnClickListener(v -> {

            String uname = username.getText().toString();
            String upass = password.getText().toString();

            //There should be code here that checks the database to see
            //if uname is already in the database


            if (!uname.isEmpty() && !uname.contains(" ")
                && !upass.isEmpty() && !upass.contains(" ")) {
                TextView t = findViewById(R.id.invalidCredentialsText);
                t.setText(mDatabase.toString());
                mDatabase.child("users").child(uname).setValue(upass);
                setProfile(mDatabase, uname);
                setMeals(mDatabase, uname);
                setPantry(mDatabase, uname);
                setShoppingList(mDatabase, uname);
                logincode();
            } else {
                TextView t = findViewById(R.id.invalidCredentialsText);
                t.setText("Username and Password cannot contain spaces or be null!");
            }

        });


    }

    private void setProfile(DatabaseReference mDatabase, String uname) {
        mDatabase.child("profile").child(uname).child("height").setValue(170);
        mDatabase.child("profile").child(uname).child("weight").setValue(60);
        mDatabase.child("profile").child(uname).child("gender").setValue("male");

    }
    private void setMeals(DatabaseReference mDatabase, String uname) {
        mDatabase.child("meals").child(uname).child("initmeal").child("name").setValue("initmeal");
        mDatabase.child("meals").child(uname).child("initmeal").child("price").setValue(0);
        mDatabase.child("meals").child(uname).child("initmeal").child("cals").setValue(0);

    }
    private void setPantry(DatabaseReference mDatabase, String uname) {
        mDatabase.child("pantry").child(uname).child("metadata")
                .child("nextindex").setValue(0);
    }
    private void setShoppingList(DatabaseReference mDatabase, String uname) {
        mDatabase.child("shoppinglist").child(uname).child("metadata")
                .child("nextindex").setValue(0);
    }
    protected void logincode() {
        Intent toMainActivity = new Intent(CreateAccountView.this, MainActivity.class);
        startActivity(toMainActivity);

    }
}
