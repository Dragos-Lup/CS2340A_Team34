package com.example.cs2340ateam34;

import android.util.Log;

public class Profile {

    private volatile int weight = -1;

    private volatile int height = -1;

    private volatile String gender = "Undef";

    public Profile(int height, int weight, String gender) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
