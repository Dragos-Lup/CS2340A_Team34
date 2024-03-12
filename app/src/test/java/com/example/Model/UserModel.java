package com.example.Model;

public class UserModel {

    private volatile static UserModel instance;

    private Meal meal;

    private Profile profile;

    private UserModel(Meal meal, Profile profile) {};

    public UserModel getInstance() {
        if (instance == null) {
            synchronized (UserModel.class) {
                if (instance == null) {
                    instance = new UserModel(meal, profile);
                }
            }
        }
        return instance;
    }

}
