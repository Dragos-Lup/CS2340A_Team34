package com.example.cs2340ateam34;

import java.util.ArrayList;
import java.util.List;

public class NotMakeableFilter implements RecipeFilterPattern {
    User user;
    public NotMakeableFilter() {
        user = User.getInstance();
    }

    @Override
    public List<Recipe> filterRecipes(List<Recipe> recipes) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (!user.checkRecipe(recipe)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }
}
