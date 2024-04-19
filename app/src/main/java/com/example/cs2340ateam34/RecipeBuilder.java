package com.example.cs2340ateam34;

import java.util.ArrayList;

public class RecipeBuilder {

    private String name;
    RecipeComponent recipeTop;

    public RecipeBuilder(String name) {
        this.name = name;
    }

    public void addComponent(String name, int quantity) {
        if (recipeTop == null) {
            recipeTop = new RecipeBase(name, quantity);
        } else {
            recipeTop = new RecipeDecorator(name, recipeTop, quantity);
        }
    }

    public ArrayList<RecipeComponent> recipeToArray() {
        ArrayList<RecipeComponent> rtn = new ArrayList<>();
        recipeTop.retrieveComponent(rtn);
        return rtn;
    }

    public String getName() {
        return name;
    }
}
