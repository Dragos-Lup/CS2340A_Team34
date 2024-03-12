package com.example.cs2340ateam34;

import java.util.ArrayList;

public class User {
    private volatile static User instance;

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

    public void addMeal(Meal meal) {

    }
}
