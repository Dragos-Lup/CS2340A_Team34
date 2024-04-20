package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListView extends Fragment {

    private RecyclerView shoppingrecyclerview;

    private User user;

    private Button buyItems;

    private ShoppingListAdapter adapter;

    private EditText ingredientname;
    private EditText quantity;

    private EditText calories;

    private Button enterButton;

    private TextView error;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_list_screen, container, false);
        shoppingrecyclerview = view.findViewById(R.id.shopping_list_recycler_view);
        buyItems = view.findViewById(R.id.buybutton);
        ingredientname = view.findViewById(R.id.buyingredient_name);
        quantity = view.findViewById(R.id.buyquantity);
        calories = view.findViewById(R.id.buycalories);
        enterButton = view.findViewById(R.id.buyenter_button);
        user = User.getInstance();
        shoppingrecyclerview = view.findViewById(R.id.shopping_list_recycler_view);
        error = view.findViewById(R.id.buyerror);
        setRecyclerView();
        enterButton.setOnClickListener(v -> {
            if (Integer.parseInt(quantity.getText().toString()) <= 0) {
                error.setText("Input cannot be negative");
            } else if (checkExist(ingredientname.getText().toString())) {
                error.setText("Cannot add duplicate ingredients");
            } else if (Integer.parseInt(calories.getText().toString()) > 0) {
                error.setText("Cannot have negative calorie amount");
            }else {
                Ingredient ingredient = new Ingredient(ingredientname.getText().toString(),
                        Integer.parseInt(quantity.getText().toString()),
                        Integer.parseInt(calories.getText().toString()),
                        "");
                user.addIngredientShoppingList(ingredient);
                error.setText("Ingredient added!");
            }
            setRecyclerView();
        });
        buyItems.setOnClickListener(v -> {

            for (int i = 0; i < user.getShoppingList().size();) {
                if (user.getShoppingList().get(i).getBuying()) {
                    user.buyIngredient(user.getShoppingList().get(i));
                } else {
                    i++;
                }
            }
            user.getActivity().loadFragment(new ShoppingListView(), false, user);
        });
        return view;

    }


    private void setRecyclerView() {
        shoppingrecyclerview.setHasFixedSize(true);
        shoppingrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShoppingListAdapter(getContext(), user.getShoppingList());
        shoppingrecyclerview.setAdapter(adapter);
    }
    boolean checkExist(String ingredientName) {
        ArrayList<Ingredient> shoppingList = user.getShoppingList();
        for (Ingredient ingredient: shoppingList) {
            if (ingredientName.equals(ingredient.getIngredientName())) {
                return true;
            }
        }
        return false;
    }


}
