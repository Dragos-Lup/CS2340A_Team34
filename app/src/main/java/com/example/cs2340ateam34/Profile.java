package com.example.cs2340ateam34;

public class Profile {

    private int weight;

    private int height;

    private String gender;

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
