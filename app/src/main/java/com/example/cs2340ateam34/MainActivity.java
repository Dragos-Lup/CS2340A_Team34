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
        Log.d("Main", "Entered main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String uname = getIntent().getExtras().getString("uname");
        Log.d("Main", uname);
        User user = User.getInstance(uname);
        user.setActivity(this);
        Log.d("MAINGENDER", user.getProfGender());
        Log.d("MAINHEIGHT", "" + user.getProfHeight());
        loadFragment(new HomeView(), false, user);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.home) {
                            loadFragment(new HomeView(), false, user);
                        } else if (itemId == R.id.inputmeal) {
                            loadFragment(new InputMealView(), false, user);
                        } else if (itemId == R.id.recipe) {
                            loadFragment(new RecipeView(), false, user);
                        } else if (itemId == R.id.ingredients) {
                            loadFragment(new IngredientsView(), false, user);
                        } else if (itemId == R.id.shoppinglist) {
                            loadFragment(new ShoppingListView(), false, user);
                        } /*else if (itemId == R.id.personalinformation) {
                            loadFragment(new PersonalInformationView(), false);
                        }*/

                        //loadFragment(new HomeView(), false);

                        return true;
                    }
                });


    }
    public void loadFragment(Fragment fragment, boolean isAppInitialized, User user) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Remove any existing fragment
        Fragment existingFragment = fragmentManager.findFragmentById(R.id.frameLayout);
        if (existingFragment != null) {
            fragmentTransaction.remove(existingFragment);
            fragmentManager.executePendingTransactions();
        }
        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }

        fragmentTransaction.commit();
    }

}