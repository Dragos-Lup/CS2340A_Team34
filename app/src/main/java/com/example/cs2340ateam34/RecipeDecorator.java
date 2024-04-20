package com.example.cs2340ateam34;

import java.util.ArrayList;

public class RecipeDecorator implements RecipeComponent {
    private String name;
    private RecipeComponent subComponent;
    private int quantity;

    public RecipeDecorator(String name, RecipeComponent subComponent, int quantity) {
        this.name = name;
        this.subComponent = subComponent;
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
        subComponent.retrieveComponent(components);
        components.add(this);
    }
}
