package com.example.cs2340ateam34;

public interface RecipeVisitor {
    public abstract void display(RecipeBuilder recipe);
    public abstract void display(MakeableRecipe recipe);

    public abstract void display(NotMakeableRecipe recipe);
}
