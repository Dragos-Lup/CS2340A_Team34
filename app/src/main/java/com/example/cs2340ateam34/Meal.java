package com.example.cs2340ateam34;

public class Meal {
    private String mealName;
    private int calories;
    private int price;

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
}
