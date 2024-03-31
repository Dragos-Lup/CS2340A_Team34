package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeView extends Fragment {

    EditText recipeName;
    EditText items;
    Button enter;

    RecyclerView recipe_recycler_view;
    RecipeAdapter adapter;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_screen, container, false);

        recipe_recycler_view = view.findViewById(R.id.recipe_recycler_view);
        recipeName = view.findViewById(R.id.recipe_name);
        items = view.findViewById(R.id.recipeitems);
        enter = view.findViewById(R.id.recipeenter);

        user = User.getInstance();
        setRecyclerView();
        enter.setOnClickListener(v -> {
            String name = recipeName.getText().toString();
            ArrayList<RecipeItem> recipeItems = new ArrayList<>();
            String itemsraw = items.getText().toString();
            String[] itemssep = itemsraw.split(",");
            for (String itemraw : itemssep) {
                String[] item = itemraw.split("-");
                String itemname = item[0].trim();
                int itemquantity = Integer.parseInt(item[1].trim());
                RecipeItem recipeItem = new RecipeItem(itemname, itemquantity);
                recipeItems.add(recipeItem);
            }
            Recipe recipe = new Recipe(name, recipeItems);
            user.addRecipe(recipe);
            setRecyclerView();
            recipeName.setText("");
            items.setText("");
        });
        return view;
    }
    private void setRecyclerView() {
        recipe_recycler_view.setHasFixedSize(true);
        recipe_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecipeAdapter(getContext(), user.getRecipeList());
        recipe_recycler_view.setAdapter(adapter);
    }

    // create the recipeview class
}
