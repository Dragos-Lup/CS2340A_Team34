package com.example.cs2340ateam34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context context;
    private List<RecipeBuilder> recipeList;

    public RecipeAdapter(Context context, List<RecipeBuilder> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.recipe_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (recipeList != null && !recipeList.isEmpty()) {
            RecipeBuilder model = recipeList.get(position).setMakeability();
            RecipeVisitor displayer = new RecipeRowVisitor(holder);
            displayer.display(model);
        //            holder.recipenametv.setText(model.getName());
        //            User user = User.getInstance();
        //            boolean canMake = user.checkRecipe(model);
        //            holder.recipemaketv.setText(canMake ? "Yes" : "No");
        //            String popupText = "" +  model.getName() + " details:\n ";
        //            holder.shopButton.setVisibility(canMake ? INVISIBLE : VISIBLE);
        //            holder.shopButton.setOnClickListener(v -> {
        //                user.shopIngredients(model.recipeToArray());
        //            });
        //            for (RecipeComponent item : model.recipeToArray()) {
        //                popupText += "" + item.getName() + " - " + item.getQuantity() + "\n";
        //            }
        //            holder.cookbutton.setOnClickListener(v -> {
        //                user.cookRecipe(model);
        //            });
        //            holder.recipepopup.setText(popupText);
        //            holder.recipenametv.setOnClickListener(v -> {
        //                if (canMake) {
        //                    holder.recipepopup.setVisibility(VISIBLE);
        //                    holder.cookbutton.setVisibility(VISIBLE);
        //                }
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView recipenametv;
        private TextView recipemaketv;
        private TextView recipepopup;
        private Button cookbutton;
        private Button shopButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipenametv = itemView.findViewById(R.id.recipe_name_tv);
            recipemaketv = itemView.findViewById(R.id.recipe_make_tv);
            recipepopup = itemView.findViewById(R.id.recipe_popup);
            cookbutton = itemView.findViewById(R.id.cookbutton_popup);
            shopButton = itemView.findViewById(R.id.shopbutton);
        }

        public TextView getRecipenametv() {
            return recipenametv;
        }

        public TextView getRecipemaketv() {
            return recipemaketv;
        }

        public TextView getRecipepopup() {
            return recipepopup;
        }

        public Button getCookbutton() {
            return cookbutton;
        }

        public Button getShopButton() {
            return shopButton;
        }
    }
}
