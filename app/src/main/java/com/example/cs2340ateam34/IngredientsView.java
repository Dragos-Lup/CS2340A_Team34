package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientsView extends Fragment {

    private EditText ingredientName;
    private EditText ingredientQuantity;
    private EditText ingredientCalories;
    private EditText ingredientExpiry;

    private RecyclerView recyclerview;
    private IngredientAdapter adapter;
    private User user;

    private TextView errorDisplay;
    private TableLayout ingredientsTable;

    private Button enterButton;
    private Button newIngredientButton;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients_screen, container, false);
        user = User.getInstance();
        ingredientName = view.findViewById(R.id.ingredient_name);
        ingredientQuantity = view.findViewById(R.id.quantity);
        ingredientCalories = view.findViewById(R.id.calories);
        ingredientExpiry = view.findViewById(R.id.expiry);
        ingredientsTable = view.findViewById(R.id.ingredient_table);
        enterButton = view.findViewById(R.id.enter);


        errorDisplay = view.findViewById(R.id.error);
        recyclerview = view.findViewById(R.id.recycler_view);
        setRecyclerView();
        newIngredientButton = view.findViewById(R.id.new_ingredient);

        formToggle(false);
        newIngredientButton.setOnClickListener(v -> {
            formToggle(true);
        });


        enterButton.setOnClickListener(v -> {
            ArrayList<EditText> texts = new ArrayList<>();
            texts.add(ingredientName);
            texts.add(ingredientQuantity);
            texts.add(ingredientCalories);
            texts.add(ingredientExpiry);
            if (TextChecker.checkEmpty(texts)) {
                errorDisplay.setText("Inputs cannot be empty");
            } else if (Integer.parseInt(ingredientQuantity.getText().toString()) <= 0) {
                errorDisplay.setText("Input cannot be negative");
            } else if (checkExist(ingredientName.getText().toString())) {
                errorDisplay.setText("Cannot add duplicate ingredients");
            } else {
                Ingredient ingredient = new Ingredient(ingredientName.getText().toString(),
                        Integer.parseInt(ingredientQuantity.getText().toString()),
                        Integer.parseInt(ingredientCalories.getText().toString()),
                        ingredientExpiry.getText().toString());
                user.addIngredient(ingredient);
                errorDisplay.setText("Ingredient added!");
            }
            formToggle(false);
            setRecyclerView();
        });

        return view;
    }

    private void setRecyclerView() {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new IngredientAdapter(getContext(), user.getIngredientList());
        recyclerview.setAdapter(adapter);
    }
    private void formToggle(boolean showing) {
        int val = showing ? View.VISIBLE : View.INVISIBLE;
        int alt = showing ? View.INVISIBLE : View.VISIBLE;
        ingredientName.setVisibility(val);
        ingredientQuantity.setVisibility(val);
        ingredientCalories.setVisibility(val);
        ingredientExpiry.setVisibility(val);
        enterButton.setVisibility(val);
        newIngredientButton.setVisibility(alt);
        ingredientsTable.setVisibility(alt);
        errorDisplay.setVisibility(alt);

        ingredientName.setText("");
        ingredientQuantity.setText("");
        ingredientCalories.setText("");
        ingredientExpiry.setText("");
    }
    boolean checkExist(String ingredientName) {
        ArrayList<Ingredient> ingredientsList = user.getIngredientList();
        for (Ingredient ingredient: ingredientsList) {
            if (ingredientName.equals(ingredient.getIngredientName())) {
                return true;
            }
        }
        return false;
    }
}
