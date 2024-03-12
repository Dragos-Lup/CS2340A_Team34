package com.example.cs2340ateam34;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputMealView extends Fragment {
    private EditText mealName;

    private EditText calories;

    private EditText price;

    private DatabaseReference meals;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_meal, container, false);

        mealName = view.findViewById(R.id.mealtext);
        calories = view.findViewById(R.id.calorietext);
        price = view.findViewById(R.id.pricetext);

        Meal inputMeal = new Meal(mealName.getText().toString(), Integer.parseInt(calories.getText().toString()), Integer.parseInt(price.getText().toString()));

        User user = User.getInstance();

        Button submit = view.findViewById(R.id.submit_button);
        submit.setOnClickListener(v -> {
            user.addMeal(inputMeal);
//            user.
        });

        Button priceVisual = view.findViewById(R.id.price_visual);
        submit.setOnClickListener(v -> {

        });

        Button calorieVisual = view.findViewById(R.id.cal_visual);
        submit.setOnClickListener(v -> {


        });
        return view;
    }



}
