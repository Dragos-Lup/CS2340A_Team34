package com.example.cs2340ateam34;

import android.widget.EditText;

import java.util.ArrayList;

public class TextChecker {
    public static boolean checkEmpty(ArrayList<EditText> texts) {
        for (EditText text : texts) {
            if (text.getText().toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
