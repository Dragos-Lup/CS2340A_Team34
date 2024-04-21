package com.example.cs2340ateam34;

import java.util.ArrayList;
import java.util.List;

public class NotMakeableFilter implements RecipeFilterPattern {
    private User user;
    public NotMakeableFilter() {
        user = User.getInstance();
    }

    @Override
    public List<RecipeBuilder> filterRecipes(List<RecipeBuilder> recipes) {
        List<RecipeBuilder> filteredRecipes = new ArrayList<>();
        for (RecipeBuilder recipe : recipes) {
            if (!user.checkRecipe(recipe)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }
}
