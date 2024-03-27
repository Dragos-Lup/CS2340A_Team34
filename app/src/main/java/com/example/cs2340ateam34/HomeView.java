package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeView extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);
        Button showProfileButton = view.findViewById(R.id.showProfile);
        TextView homeTitle = view.findViewById(R.id.textView6);
        // Set click listener for the button
        showProfileButton.setOnClickListener(v -> {
            PersonalInformationView profileFragment = new PersonalInformationView();
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.profileframe, profileFragment).commit();
            showProfileButton.setVisibility(View.INVISIBLE);
            homeTitle.setVisibility(View.INVISIBLE);
        });

        return view;
    }

}
