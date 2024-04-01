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

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private Context context;
    private List<Ingredient> ingredientList;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
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
        if (ingredientList != null && ingredientList.size() > 0) {
            Ingredient model = ingredientList.get(position);
            holder.nametv.setText("" + model.getIngredientName());
            holder.quantitytv.setText("" + model.getQuantity());
            User user = User.getInstance();
            holder.addtv.setOnClickListener(v -> {
                user.updateIngredient(model, 1);
                holder.quantitytv.setText("" + model.getQuantity());
            });
            holder.deltv.setOnClickListener(v -> {
                user.updateIngredient(model, -1);
                holder.quantitytv.setText("" + model.getQuantity());
                user.getActivity().loadFragment(new IngredientsView(), false, user);
            });
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nametv;
        private TextView quantitytv;
        private Button addtv;
        private Button deltv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nametv = itemView.findViewById(R.id.name_tv);
            quantitytv = itemView.findViewById(R.id.quantity_tv);
            addtv = itemView.findViewById(R.id.add_tv);
            deltv = itemView.findViewById(R.id.del_tv);

        }
    }
}
