package com.example.cs2340ateam34;

public class RecipePopupVisitor implements RecipeVisitor{

    RecipeAdapter.ViewHolder holder;

    public RecipePopupVisitor(RecipeAdapter.ViewHolder holder) {
        this.holder = holder;
    }

    @Override
    public void visit(MakeableRecipe recipe) {
        holder.getRecipepopup();
    }

    @Override
    public void visit(NotMakeableRecipe recipe) {

    }
}
