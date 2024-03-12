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

//    private volatile static int height = -1;
//    private volatile static int weight = -1;
//    private volatile static String gender = "Undefined";
    private ArrayList<Meal> mealList;

    private volatile Profile profile;

    public int calorieGoal; // made from formula

    private User() {};

    public static User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    //add code to fetch meal list and profile from relevant user account in database
                    instance = new User();
                    instance.mealList = new ArrayList<>();
                    instance.profile = new Profile(-1, -1, "Undefined");
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
                    instance.profile= new Profile(-1, -1, "Undefined");
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
                        instance.profile.setGender(x);
                    }
                });
        dbRef.child("profile").child(uname).child("height").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("Height", x);
                        instance.profile.setHeight(Integer.parseInt(x));
                    }
                });
        dbRef.child("profile").child(uname).child("weight").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("Weight", x);
                        instance.profile.setWeight(Integer.parseInt(x));
                    }
                });

    }
    public void addMeal(Meal meal) {
        mealList.add(meal);
    }

    public String getUname(){
        return uname;
    }
    public String getProfGender(){
        return profile.getGender();
    }
    public void setProfGender(String inputGender){
        profile.setGender(inputGender);
        dbRef.child("profile").child(uname).child("gender").setValue(inputGender);
    }
    public int getProfHeight(){
        return profile.getHeight();
    }
    public void setProfHeight(int inputHeight){
        profile.setHeight(inputHeight);
        dbRef.child("profile").child(uname).child("height").setValue(inputHeight);
    }
    public int getProfWeight(){
        return profile.getWeight();
    }
    public void setProfWeight(int inputWeight){
        profile.setWeight(inputWeight);
        dbRef.child("profile").child(uname).child("weight").setValue(inputWeight);
    }

    public ArrayList<Meal> getMealList() {
        return mealList;
    }
}
