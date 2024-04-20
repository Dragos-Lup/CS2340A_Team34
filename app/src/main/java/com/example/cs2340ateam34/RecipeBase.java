package com.example.cs2340ateam34;

import java.util.ArrayList;

public class RecipeBase implements RecipeComponent {
    private String name;
    private int quantity;

    public RecipeBase(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void retrieveComponent(ArrayList<RecipeComponent> components) {
        components.add(this);
    }
}
