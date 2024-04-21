package com.example.cs2340ateam34;

public interface RecipeVisitor {
    public abstract void visit(MakeableRecipe recipe);

    public abstract void visit(NotMakeableRecipe recipe);
}
