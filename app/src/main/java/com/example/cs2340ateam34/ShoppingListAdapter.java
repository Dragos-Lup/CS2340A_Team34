package com.example.cs2340ateam34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>{

    private Context context;
    private List<Ingredient> ingredientList;


    public ShoppingListAdapter(Context context, List<Ingredient> ingredientlist) {
        this.context = context;
        this.ingredientList = ingredientlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {

    }

    public int getItemCount() {
        return ingredientList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
