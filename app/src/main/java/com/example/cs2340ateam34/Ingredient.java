package com.example.cs2340ateam34;

public class Ingredient {
    private String ingredientName;
    private int quantity;
    private int calories;
    private String expiry;

    public Ingredient(String ingredientName, int quantity, int calories, String expiry){
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.calories = calories;
        this.expiry = expiry;
    }

    public String getIngredientName(){
        return ingredientName;
    }

    public int getQuantity(){
        return quantity;
    }
    public int getCalories(){
        return calories;
    }
    public String getExpiry(){
        return expiry;
    }
}
