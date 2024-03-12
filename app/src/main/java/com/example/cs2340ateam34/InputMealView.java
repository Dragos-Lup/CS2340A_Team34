package com.example.cs2340ateam34;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;

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



        User user = User.getInstance();
        TextView heightDisplay = view.findViewById(R.id.user_height);
        TextView weightDisplay = view.findViewById(R.id.user_weight);
        TextView genderDisplay = view.findViewById(R.id.user_gender);
        TextView calorieGoalDisplay = view.findViewById(R.id.caloriegoal);
        TextView currentCaloriesDisplay = view.findViewById(R.id.current_calories);
        heightDisplay.setText("Height: " + user.getProfHeight());
        weightDisplay.setText("Weight: " + user.getProfWeight());
        genderDisplay.setText("Gender: " + user.getProfGender());

        double calgoal = 10 * user.getProfWeight() + 6.25 * user.getProfHeight() - 100 + 5;
        if (user.getProfGender().equals("female")){
            calgoal -= 166;
        }
        calorieGoalDisplay.setText("Calorie Goal: " + calgoal);
        currentCaloriesDisplay.setText("Current Day Calorie Intake: " + user.getCurrDayCalorieIntake());

        Button submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            Log.d("currcal", "in");
            Meal inputMeal = new Meal(mealName.getText().toString(), Integer.parseInt(calories.getText().toString()), Integer.parseInt(price.getText().toString()));
            user.addMeal(inputMeal);
            Log.d("currcal", "addedmeal");
            double newCalories = user.getCurrDayCalorieIntake();
            Log.d("currcal", "" + newCalories);
            currentCaloriesDisplay.setText("Current Day Calorie Intake: " + newCalories);

        });

        Button priceVisual = view.findViewById(R.id.price_visual);
        priceVisual.setOnClickListener(v -> {

        });

        Button calorieVisual = view.findViewById(R.id.cal_visual);
        calorieVisual.setOnClickListener(v -> {


        });
        return view;
    }



}
