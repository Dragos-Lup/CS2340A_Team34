package com.example.cs2340ateam34;

import static android.view.View.VISIBLE;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecipeRowVisitor implements RecipeVisitor{

    RecipeAdapter.ViewHolder holder;
    private TextView recipenametv;
    private TextView recipemaketv;
    private TextView recipepopup;
    private Button cookbutton;
    private Button shopButton;
    User user;

    public RecipeRowVisitor(RecipeAdapter.ViewHolder holder) {
        this.holder = holder;
        recipenametv = holder.getRecipenametv();
        recipemaketv = holder.getRecipemaketv();
        recipepopup = holder.getRecipepopup();
        cookbutton = holder.getCookbutton();
        shopButton = holder.getShopButton();
        user = User.getInstance();
    }

    @Override
    public void display(RecipeBuilder recipe) {
        if (recipe instanceof MakeableRecipe) {
            display((MakeableRecipe) recipe);
        } else if (recipe instanceof NotMakeableRecipe) {
            display((NotMakeableRecipe) recipe);
        }
    }

    @Override
    public void display(MakeableRecipe recipe) {
        recipenametv.setText(recipe.getName());
        shopButton.setVisibility(View.INVISIBLE);
        recipemaketv.setText("Yes");
        shopButton.setOnClickListener(v -> {
            user.shopIngredients(recipe.recipeToArray());
        });
        recipenametv.setOnClickListener(v -> {
                recipepopup.setVisibility(View.VISIBLE);
                cookbutton.setVisibility(View.VISIBLE);
        });
        String popupText = "" +  recipe.getName() + " details:\n ";
        for (RecipeComponent item : recipe.recipeToArray()) {
            popupText += "" + item.getName() + " - " + item.getQuantity() + "\n";
        }
        cookbutton.setOnClickListener(v -> {
            user.cookRecipe(recipe);
        });
        recipepopup.setText(popupText);
    }

    @Override
    public void display(NotMakeableRecipe recipe) {
        recipenametv.setText(recipe.getName());
        shopButton.setVisibility(View.VISIBLE);
        recipemaketv.setText("No");
        shopButton.setOnClickListener(v -> {
            user.shopIngredients(recipe.recipeToArray());
        });
        String popupText = "" +  recipe.getName() + " details:\n ";
        for (RecipeComponent item : recipe.recipeToArray()) {
            popupText += "" + item.getName() + " - " + item.getQuantity() + "\n";
        }
        cookbutton.setOnClickListener(v -> {
            user.cookRecipe(recipe);
        });
        recipepopup.setText(popupText);
        recipenametv.setOnClickListener(v -> {
            recipepopup.setVisibility(View.INVISIBLE);
            cookbutton.setVisibility(View.INVISIBLE);
        });
    }
}
