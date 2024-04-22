package com.example.cs2340ateam34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private Context context;
    private List<Ingredient> shoppingList;


    public ShoppingListAdapter(Context context, List<Ingredient> shoppinglist) {
        this.context = context;
        this.shoppingList = shoppinglist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.buy_layout, parent, false);
        return new ShoppingListAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {
        if (shoppingList != null && shoppingList.size() > 0) {
            Ingredient model = shoppingList.get(position);
            holder.nametv.setText("" + model.getIngredientName());
            holder.quantitytv.setText("" + model.getQuantity());
            User user = User.getInstance();
            holder.addtv.setOnClickListener(v -> {
                user.updateIngredientShoppingList(model, 1);
                holder.quantitytv.setText("" + model.getQuantity());
            });
            holder.deltv.setOnClickListener(v -> {
                user.updateIngredientShoppingList(model, -1);
                holder.quantitytv.setText("" + model.getQuantity());
                user.getActivity().loadFragment(new ShoppingListView(), false, user);
            });
            holder.buytv.setOnClickListener(v -> {
                model.setBuying(!model.getBuying());
            });
        } else {
            return;
        }
    }

    public int getItemCount() {
        return shoppingList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nametv;
        private TextView quantitytv;
        private Button addtv;
        private Button deltv;
        private CheckBox buytv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nametv = itemView.findViewById(R.id.buyname_tv);
            quantitytv = itemView.findViewById(R.id.buyquantity_tv);
            addtv = itemView.findViewById(R.id.buyadd_tv);
            deltv = itemView.findViewById(R.id.buydel_tv);
            buytv = itemView.findViewById(R.id.buy_tv);


        }
    }
}
