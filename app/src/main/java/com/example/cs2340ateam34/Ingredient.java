package com.example.cs2340ateam34;

public class Ingredient {
    private String ingredientName;
    private int quantity;
    private int calories;
    private String expiry;

    private boolean buying;
    private int index;

    public Ingredient(String ingredientName, int quantity, int calories, String expiry) {
        this(ingredientName, quantity, calories, expiry, -1);
    }
    public Ingredient(String ingredientName, int quantity, int calories, String expiry, int index) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.calories = calories;
        this.expiry = expiry;
        this.index = index;
        this.buying = false;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getCalories() {
        return calories;
    }
    public String getExpiry() {
        return expiry;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public void setBuying(boolean b) {
        this.buying = b;
    }
    public boolean getBuying(){
        return this.buying;
    }
}
