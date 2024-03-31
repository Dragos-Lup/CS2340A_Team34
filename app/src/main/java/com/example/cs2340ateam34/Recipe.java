package com.example.cs2340ateam34;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<RecipeItem> recipeItems;

    public Recipe(String name, ArrayList<RecipeItem> recipeItems){
        this.name = name;
        this.recipeItems = recipeItems;
    }
    public Recipe(String name){
        this(name,null);
    }
    public String getName(){
        return name;
    }
    public ArrayList<RecipeItem> getRecipeItems(){
        return recipeItems;
    }

}
