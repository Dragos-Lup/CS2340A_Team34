package com.example.cs2340ateam34;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ShoppingListView extends Fragment {

    private RecyclerView shoppingrecyclerview;

    private User user;

    private Button buyItems;

    private ShoppingListAdapter adapter;

    private FoodPatternFilter filter;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_list_screen, container, false);
        setFilter(filter);
        shoppingrecyclerview = view.findViewById(R.id.shopping_list_recycler_view);
        buyItems = view.findViewById(R.id.button);

        user = User.getInstance();
        setRecyclerView();
        return view;

    }

    private void setFilter(FoodPatternFilter input) {
        filter = input;
    }

    private void setRecyclerView() {
        shoppingrecyclerview.setHasFixedSize(true);
        shoppingrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShoppingListAdapter(getContext(), filter.filterFood(user.getIngredientList()));
        shoppingrecyclerview.setAdapter(adapter);
    }



}
