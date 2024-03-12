package com.example.cs2340ateam34;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Main","Entered main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String uname = getIntent().getExtras().getString("uname");
        Log.d("Main", uname);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.home) {
                            loadFragment(new HomeView(), false);
                        } else if (itemId == R.id.inputmeal) {
                            loadFragment(new InputMealView(), false);
                        } else if (itemId == R.id.recipe) {
                            loadFragment(new RecipeView(), false);
                        } else if (itemId == R.id.ingredients) {
                            loadFragment(new IngredientsView(), false);
                        } else if (itemId == R.id.shoppinglist) {
                            loadFragment(new ShoppingListView(), false);
                        } /*else if (itemId == R.id.personalinformation) {
                            loadFragment(new PersonalInformationView(), false);
                        }*/

                        loadFragment(new HomeView(), true);

                        return true;
                    }
                });


    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }

        fragmentTransaction.commit();
    }
}