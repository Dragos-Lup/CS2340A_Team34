package com.example.cs2340ateam34;

import java.util.ArrayList;

public interface RecipeComponent {

    public abstract String getName();

    public abstract int getQuantity();
    public abstract void retrieveComponent(ArrayList<RecipeComponent> components);
}
