package com.example.cs2340ateam34;

import java.util.Date;

public class Meal {
    private String mealName;
    private int calories;
    private int price;
    private Date date;

    public Meal(String mealName, int calories, int price) {
        this.mealName = mealName;
        this.calories = calories;
        this.price = price;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
