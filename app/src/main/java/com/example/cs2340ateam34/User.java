package com.example.cs2340ateam34;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class User {
    private static volatile User instance;

    private static DatabaseReference dbRef;

    private static String uname;

    private static ArrayList<Meal> mealList;

    private volatile Profile profile;



    private User() {
    }

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
                    initMeals(unameParam);

                    instance = new User();
                    instance.mealList = new ArrayList<>();
                    instance.profile = new Profile(-1, -1, "Undefined");
                }
            }
        }
        return instance;
    }


    private static void initProfile(String uname) {
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
    private static void initMeals(String uname) {

        Log.d("meals", "meals");
        dbRef.child("meals").child(uname).get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Log.d("meals", "meals");
                        Map data = (Map) (task.getResult().getValue());
                        Log.d("meals", "mealsadfa");
                        Set<String> keyset = data.keySet();
                        Meal[] tempmeallist = new Meal[keyset.size() - 1];
                        for (String key : keyset) {
                            if (key.equals("initmeal")) {
                                continue;
                            }
                            Log.d("madsf", key);
                            Map mealMap = (Map) (data.get(key));
                            String name = mealMap.get("name").toString();
                            int calories = Integer.parseInt(mealMap.get("calories").toString());
                            int price = Integer.parseInt(mealMap.get("price").toString());
                            String date = mealMap.get("date").toString();
                            Meal meal = new Meal(name, calories, price, date);
                            tempmeallist[Integer.parseInt(key)] = meal;
                            Log.d("madsfnamename", name);
                            Log.d("madsfnamecalories", "" + calories);
                            Log.d("madsfnameprice", "" + price);
                            Log.d("madsfnamedate",  date);
                        }
                        for (Meal meal : tempmeallist) {
                            mealList.add(meal);
                        }
                    }
                });
    }
    public void addMeal(Meal meal) {
        // Get current date
        Date currentDate = new Date();

        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        // Format the current date
        String formattedDate = dateFormat.format(currentDate);

        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("name").setValue(meal.getMealName());
        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("calories").setValue(meal.getCalories());
        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("price").setValue(meal.getPrice());
        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("date").setValue(formattedDate);
        mealList.add(meal);

    }

    public String getUname() {
        return uname;
    }
    public String getProfGender() {
        Log.d("h", "h");
        return profile.getGender();
    }
    public void setProfGender(String inputGender) {
        profile.setGender(inputGender);
        dbRef.child("profile").child(uname).child("gender").setValue(inputGender);
    }
    public int getProfHeight() {
        return profile.getHeight();
    }
    public void setProfHeight(int inputHeight) {
        profile.setHeight(inputHeight);
        dbRef.child("profile").child(uname).child("height").setValue(inputHeight);
    }
    public int getProfWeight() {
        return profile.getWeight();
    }
    public void setProfWeight(int inputWeight) {
        profile.setWeight(inputWeight);
        dbRef.child("profile").child(uname).child("weight").setValue(inputWeight);
    }

    public double getCurrDayCalorieIntake() {
        // Get current date
        Date currentDate = new Date();

        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        // Format the current date
        String formattedDate = dateFormat.format(currentDate);

        double out = 0;
        for (Meal meal : mealList) {
            if (meal.getDate().equals(formattedDate)) {
                out += meal.getCalories();
            }
        }
        return out;
    }
    public ArrayList<Meal> getMealList() {
        return mealList;
    }
}
