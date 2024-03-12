package com.example.cs2340ateam34;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {
    private volatile static User instance;

    private static DatabaseReference dbRef;

    private static String uname;

    private volatile static int height = -1;
    private volatile static int weight = -1;
    private volatile static String gender = "Undefined";
    private ArrayList<Meal> mealList;

    private Profile profile;

    public int calorieGoal; // made from formula

    private User() {};

    public static User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    //add code to fetch meal list and profile from relevant user account in database
                    instance = new User();
                    instance.mealList = new ArrayList<>();
                    instance.profile= new Profile();
                }
            }
        }
        return instance;
    }
    public static User getInstance(String unameParam) {

        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    //add code to fetch meal list and profile from relevant user account in database
                    dbRef = FirebaseDatabase.getInstance().getReference();
                    uname = unameParam;

                    initProfile(uname);

                    instance = new User();
                    instance.mealList = new ArrayList<>();
                    instance.profile= new Profile();
                }
            }
        }
        return instance;
    }


    private static void initProfile(String uname){
        Log.d("INITING", "initing");
        dbRef.child("profile").child(uname).child("gender").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("GENDER", x);
                        gender = x;
                    }
                });
        dbRef.child("profile").child(uname).child("height").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("Height", x);
                        height = Integer.parseInt(x);
                    }
                });
        dbRef.child("profile").child(uname).child("weight").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("Weight", x);
                        weight = Integer.parseInt(x);
                    }
                });

    }
    public void addMeal(Meal meal) {

    }

    public String getUname(){
        return uname;
    }
    public String getProfGender(){
        return gender;
    }
    public void setProfGender(String inputGender){
        gender = inputGender;
        dbRef.child("profile").child(uname).child("gender").setValue(inputGender);
    }
    public int getProfHeight(){
        return height;
    }
    public void setProfHeight(int inputHeight){
        height = inputHeight;
        dbRef.child("profile").child(uname).child("height").setValue(inputHeight);
    }
    public int getProfWeight(){
        return weight;
    }
    public void setProfWeight(int inputWeight){
        weight = inputWeight;
        dbRef.child("profile").child(uname).child("weight").setValue(inputWeight);
    }
}
