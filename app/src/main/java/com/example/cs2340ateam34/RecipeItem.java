package com.example.cs2340ateam34;

public class RecipeItem {
    private String name;
    private int quantity;
    public RecipeItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
