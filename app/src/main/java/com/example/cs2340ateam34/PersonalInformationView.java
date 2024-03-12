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

public class PersonalInformationView extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_information_screen, container, false);
        Button updateButton = view.findViewById(R.id.updatebutton);
        EditText heightInput = view.findViewById(R.id.heighttext);
        EditText weightInput = view.findViewById(R.id.weighttext);
        EditText genderInput = view.findViewById(R.id.gendertext);
        TextView heightDisplay = view.findViewById(R.id.heightdisplay);
        TextView weightDisplay = view.findViewById(R.id.weightdisplay);
        TextView genderDisplay = view.findViewById(R.id.genderdisplay);
        User user = User.getInstance();
        Log.d("before", "after");
        heightDisplay.setText("Height: " + user.getProfHeight());
        weightDisplay.setText("Weight: " + user.getProfWeight());
        genderDisplay.setText("Gender: " + user.getProfGender());


        updateButton.setOnClickListener(v -> {
            String newheight = heightInput.getText().toString();
            String newweight = weightInput.getText().toString();
            String newgender = genderInput.getText().toString();
            if(!newheight.equals("")) {
                heightDisplay.setText("Height: " + newheight);
                user.setProfHeight(Integer.parseInt(newheight));
            }
            if(!newweight.equals("")) {
                weightDisplay.setText("Weight: " + newweight);
                user.setProfWeight(Integer.parseInt(newweight));
            }
            if(!newgender.equals("")) {
                genderDisplay.setText("Gender: " + newgender);
                user.setProfGender(newgender);
            }

        });

        return view;
    }

}
