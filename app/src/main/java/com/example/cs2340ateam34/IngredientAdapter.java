package com.example.cs2340ateam34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{

    Context context;
    List<Ingredient> ingredientList;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList){
        this.context = context;
        this.ingredientList = ingredientList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (ingredientList != null && ingredientList.size() > 0){
            Ingredient model = ingredientList.get(position);
            holder.name_tv.setText("" + model.getIngredientName());
            holder.quantity_tv.setText("" + model.getQuantity());
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv, quantity_tv, add_tv, del_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.name_tv);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);
            add_tv = itemView.findViewById(R.id.add_tv);
            del_tv = itemView.findViewById(R.id.del_tv);

        }
    }
}
