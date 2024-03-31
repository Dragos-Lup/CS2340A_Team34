package com.example.cs2340ateam34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    Context context;
    List<Recipe> recipeList;

    public RecipeAdapter(Context context, List<Recipe> recipeList){
        this.context = context;
        this.recipeList = recipeList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (recipeList != null && recipeList.size() > 0) {
            Recipe model = recipeList.get(position);
            holder.recipe_name_tv.setText(model.getName());
            User user = User.getInstance();
            boolean canMake = user.checkRecipe(model);
            holder.recipe_make_tv.setText(canMake ? "Yes" : "No");
            String popupText = "" +  model.getName() + " details:\n ";
            for(RecipeItem item : model.getRecipeItems()) {
                popupText += "" + item.getName() + " - " + item.getQuantity() + "\n";
            }
            holder.recipe_popup.setText(popupText);
            holder.recipe_name_tv.setOnClickListener(v -> {
                if (canMake) {
                    holder.recipe_popup.setVisibility(View.VISIBLE);
                }
            });
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipe_name_tv, recipe_make_tv, recipe_popup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipe_name_tv = itemView.findViewById(R.id.recipe_name_tv);
            recipe_make_tv = itemView.findViewById(R.id.recipe_make_tv);
            recipe_popup = itemView.findViewById(R.id.recipe_popup);

        }
    }
}
