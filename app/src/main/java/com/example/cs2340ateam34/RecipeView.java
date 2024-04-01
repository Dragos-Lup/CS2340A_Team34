package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeView extends Fragment {

    EditText recipeName;
    EditText items;
    Button enter;

    TextView recipeError;

    RadioButton filterAll;
    RadioButton filterMakeable;
    RadioButton filterNotMakeable;

    RecyclerView recipe_recycler_view;
    RecipeAdapter adapter;
    User user;

    RecipeFilterPattern filter;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_screen, container, false);
        setFilter(new NoFilter());
        recipe_recycler_view = view.findViewById(R.id.recipe_recycler_view);
        recipeName = view.findViewById(R.id.recipe_name);
        items = view.findViewById(R.id.recipeitems);
        enter = view.findViewById(R.id.recipeenter);
        recipeError = view.findViewById(R.id.recipe_error);
        filterAll = view.findViewById(R.id.filter_all);
        filterMakeable = view.findViewById(R.id.filter_makeable);
        filterNotMakeable = view.findViewById(R.id.filter_notMakeable);

        user = User.getInstance();
        filterAll.toggle();
        setRecyclerView();
        enter.setOnClickListener(v -> {
            String name = recipeName.getText().toString();
            ArrayList<RecipeItem> recipeItems = new ArrayList<>();
            String itemsraw = items.getText().toString();
            String[] itemssep = itemsraw.split(",");
            boolean quantityError = false;
            for (String itemraw : itemssep) {
                String[] item = itemraw.split("-");
                String itemname = item[0].trim();
                int itemquantity = Integer.parseInt(item[1].trim());
                if (itemquantity < 1) {
                    quantityError = true;
                    break;
                }
                RecipeItem recipeItem = new RecipeItem(itemname, itemquantity);
                recipeItems.add(recipeItem);
            }
            if (quantityError) {
                recipeError.setText("Input quantity cannot be less than 1.");
            } else {
                Recipe recipe = new Recipe(name, recipeItems);
                user.addRecipe(recipe);
                recipeError.setText("");
                recipeName.setText("");
                items.setText("");
            }

            setRecyclerView();
        });

        filterAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setFilter(new NoFilter());
                setRecyclerView();
            }
        });

        filterMakeable.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setFilter(new MakeableFilter());
                setRecyclerView();
            }
        });

        filterNotMakeable.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setFilter(new NotMakeableFilter());
                setRecyclerView();
            }
        });
        return view;
    }
    private void setRecyclerView() {
        recipe_recycler_view.setHasFixedSize(true);
        recipe_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecipeAdapter(getContext(), filter.filterRecipes(user.getRecipeList()));
        recipe_recycler_view.setAdapter(adapter);
    }

    // create the recipeview class

    private void setFilter(RecipeFilterPattern input) {
        filter = input;
    }
}
