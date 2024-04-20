package com.example.cs2340ateam34;

import java.util.List;

public class NoFilter implements RecipeFilterPattern {
    public NoFilter() {
    }

    @Override
    public List<RecipeBuilder> filterRecipes(List<RecipeBuilder> recipes) {
        return recipes;
    }
}
