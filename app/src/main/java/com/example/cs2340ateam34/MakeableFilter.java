package com.example.cs2340ateam34;

import java.util.ArrayList;
import java.util.List;

public class MakeableFilter implements RecipeFilterPattern {
    private User user;
    public MakeableFilter() {
        user = User.getInstance();
    }

    @Override
    public List<Recipe> filterRecipes(List<Recipe> recipes) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (user.checkRecipe(recipe)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }
}
