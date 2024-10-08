package com.example.cs2340ateam34;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class User {
    private static volatile User instance;

    private static DatabaseReference dbRef;

    private static String uname;

    private static ArrayList<Meal> mealList;


    private static ArrayList<Ingredient> ingredientList = new ArrayList<>();

    private static ArrayList<RecipeBuilder> recipeList = new ArrayList<>();
    private static ArrayList<Ingredient> shoppingList = new ArrayList<>();

    private static int nextIngredientIndex;

    private static int nextShoppingListIndex;

    private volatile Profile profile;

    private MainActivity activity;

    private User() {
    }

    public static User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    //add code to fetch meal list and profile from relevant user account in database
                    instance = new User();
                    instance.mealList = new ArrayList<>();
                    instance.profile = new Profile(-1, -1, "Undefined");
                }
            }
        }
        return instance;
    }
    public static User getInstance(String unameParam) {

        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    //add code to fetch meal list and profile from relevant user account in database
                    dbRef = FirebaseDatabase.getInstance().getReference();
                    uname = unameParam;

                    initProfile(uname);
                    initMeals(unameParam);
                    initIngredients(unameParam);
                    initRecipes(unameParam);
                    initShoppingList(unameParam);
                    instance = new User();
                    instance.mealList = new ArrayList<>();
                    instance.profile = new Profile(-1, -1, "Undefined");
                }
            }
        }
        return instance;
    }


    private static void initProfile(String uname) {
        Log.d("INITING", "initing");
        dbRef.child("profile").child(uname).child("gender").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("GENDER", x);
                        instance.profile.setGender(x);
                    }
                });
        dbRef.child("profile").child(uname).child("height").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("Height", x);
                        instance.profile.setHeight(Integer.parseInt(x));
                    }
                });
        dbRef.child("profile").child(uname).child("weight").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String x = String.valueOf(task.getResult().getValue());
                        Log.d("Weight", x);
                        instance.profile.setWeight(Integer.parseInt(x));
                    }
                });

    }
    private static void initMeals(String uname) {

        Log.d("meals", "meals");
        dbRef.child("meals").child(uname).get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Log.d("meals", "meals");
                        Map data = (Map) (task.getResult().getValue());
                        Log.d("meals", "mealsadfa");
                        Set<String> keyset = data.keySet();
                        Meal[] tempmeallist = new Meal[keyset.size() - 1];
                        for (String key : keyset) {
                            if (key.equals("initmeal")) {
                                continue;
                            }
                            Log.d("madsf", key);
                            Map mealMap = (Map) (data.get(key));
                            String name = mealMap.get("name").toString();
                            int calories = Integer.parseInt(mealMap.get("calories").toString());
                            int price = Integer.parseInt(mealMap.get("price").toString());
                            String date = mealMap.get("date").toString();
                            Meal meal = new Meal(name, calories, price, date);
                            tempmeallist[Integer.parseInt(key)] = meal;
                            Log.d("madsfnamename", name);
                            Log.d("madsfnamecalories", "" + calories);
                            Log.d("madsfnameprice", "" + price);
                            Log.d("madsfnamedate",  date);
                        }
                        for (Meal meal : tempmeallist) {
                            mealList.add(meal);
                        }
                    }
                });
    }
    private static void initIngredients(String uname) {

        dbRef.child("pantry").child(uname).get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Map data = (Map) (task.getResult().getValue());
                        Set<String> keyset = data.keySet();
                        //Ingredient[] tempingredientlist = new Ingredient[keyset.size() - 1];
                        for (String key : keyset) {
                            if (key.equals("metadata")) {
                                Map metaMap = (Map) (data.get(key));
                                nextIngredientIndex = Integer.
                                        parseInt(metaMap.get("nextindex").toString());
                                continue;
                            }
                            Log.d("madsf", key);
                            Map ingredientMap = (Map) (data.get(key));
                            String name = ingredientMap.get("name").toString();
                            int calories = Integer.parseInt(
                                    ingredientMap.get("calories").toString());
                            int quantity = Integer.parseInt(
                                    ingredientMap.get("quantity").toString());
                            String expiry = ingredientMap.get("expiry").toString();
                            int index = Integer.parseInt(key.toString());
                            Ingredient ingredient = new Ingredient(
                                    name, quantity, calories, expiry, index);
                            //tempingredientlist[Integer.parseInt(key)] = ingredient;
                            ingredientList.add(ingredient);
                        }
                        /*for (Ingredient ingredient : tempingredientlist) {
                            ingredientList.add(ingredient);
                        }*/
                    }
                });
    }
    private static void initShoppingList(String uname) {

        dbRef.child("shoppinglist").child(uname).get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Map data = (Map) (task.getResult().getValue());
                        Set<String> keyset = data.keySet();
                        //Ingredient[] tempingredientlist = new Ingredient[keyset.size() - 1];
                        for (String key : keyset) {
                            if (key.equals("metadata")) {
                                Map metaMap = (Map) (data.get(key));
                                nextShoppingListIndex = Integer.
                                        parseInt(metaMap.get("nextindex").toString());
                                continue;
                            }
                            Log.d("madsf", key);
                            Map ingredientMap = (Map) (data.get(key));
                            String name = ingredientMap.get("name").toString();
                            int calories = Integer.parseInt(
                                    ingredientMap.get("calories").toString());
                            int quantity = Integer.parseInt(
                                    ingredientMap.get("quantity").toString());
                            String expiry = ingredientMap.get("expiry").toString();
                            int index = Integer.parseInt(key.toString());
                            Ingredient ingredient = new Ingredient(
                                    name, quantity, calories, expiry, index);
                            //tempingredientlist[Integer.parseInt(key)] = ingredient;
                            shoppingList.add(ingredient);
                        }
                        /*for (Ingredient ingredient : tempingredientlist) {
                            ingredientList.add(ingredient);
                        }*/
                    }
                });
    }
    private static void initRecipes(String uname) {

        dbRef.child("recipes").get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Map data = (Map) (task.getResult().getValue());
                        Set<String> keyset = data.keySet();
                        for (String key : keyset) {
                            if (key.equals("metadata")) {
                                //Map metaMap = (Map) (data.get(key));
                                //nextIngredientIndex = Integer.parseInt(
                                // metaMap.get("nextindex").toString());
                                continue;
                            }
                            Log.d("madsf", key);
                            String name = key.toString();
                            Map recipeMap = (Map) (data.get(key));
                            RecipeBuilder recipe = new RecipeBuilder(name);
                            Set<String> innerKeySet = recipeMap.keySet();
                            for (String itemName : innerKeySet) {
                                int quantity = Integer.parseInt(recipeMap.get(itemName).toString());
                                recipe.addComponent(itemName, quantity);
                            }
                            recipeList.add(recipe);
                        }
                    }
                });
    }
    public void addMeal(Meal meal) {
        // Get current date
        Date currentDate = new Date();

        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        // Format the current date
        String formattedDate = dateFormat.format(currentDate);

        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("name").setValue(meal.getMealName());
        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("calories").setValue(meal.getCalories());
        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("price").setValue(meal.getPrice());
        dbRef.child("meals").child(uname).child(""
                + mealList.size()).child("date").setValue(formattedDate);
        mealList.add(meal);

    }

    public void addIngredientShoppingList(Ingredient ingredient) {
        ingredient.setIndex(nextShoppingListIndex);
        dbRef.child("shoppinglist").child(uname).child("" + nextShoppingListIndex).child(
                "name").setValue(ingredient.getIngredientName());
        dbRef.child("shoppinglist").child(uname).child("" + nextShoppingListIndex).child(
                "calories").setValue(ingredient.getCalories());
        dbRef.child("shoppinglist").child(uname).child("" + nextShoppingListIndex).child(
                "quantity").setValue(ingredient.getQuantity());
        dbRef.child("shoppinglist").child(uname).child("" + nextShoppingListIndex).child(
                "expiry").setValue(ingredient.getExpiry());
        shoppingList.add(ingredient);
        Log.d("" + shoppingList.size(), "asef");
        nextShoppingListIndex++;
        dbRef.child("shoppinglist").child(uname).child("metadata").child(
                "nextindex").setValue(nextShoppingListIndex);
    }
    public void addIngredientShoppingList(Ingredient ingredient, Boolean t) {
        ingredient.setIndex(nextShoppingListIndex);
        shoppingList.add(ingredient);
        nextShoppingListIndex++;
    }
    public void addIngredient(Ingredient ingredient) {
        ingredient.setIndex(nextIngredientIndex);
        Log.d("ingredientcration", "" + ingredient.getIndex() + ingredient.getIngredientName());
        dbRef.child("pantry").child(uname).child("" + nextIngredientIndex).child(
                "name").setValue(ingredient.getIngredientName());
        dbRef.child("pantry").child(uname).child("" + nextIngredientIndex).child(
                "calories").setValue(ingredient.getCalories());
        dbRef.child("pantry").child(uname).child("" + nextIngredientIndex).child(
                "quantity").setValue(ingredient.getQuantity());
        dbRef.child("pantry").child(uname).child("" + nextIngredientIndex).child(
                "expiry").setValue(ingredient.getExpiry());
        ingredientList.add(ingredient);

        nextIngredientIndex++;
        dbRef.child("pantry").child(uname).child("metadata").child(
                "nextindex").setValue(nextIngredientIndex);
    }

    public void addIngredient(Ingredient ingredient, Boolean t) {
        ingredient.setIndex(nextIngredientIndex);
        ingredientList.add(ingredient);
        nextIngredientIndex++;
    }

    public void updateIngredient(Ingredient ingredient, int value) {
        Log.d("BRUHHUUHUHUHUH", "" + ingredient.getIndex() + ingredient.getIngredientName());
        int index = ingredient.getIndex();
        if (ingredient.getQuantity() + value <= 0) {
            dbRef.child("pantry").child(uname).child("" + index).removeValue();
            Log.d("a", "" + ingredientList.size());
            ingredientList.remove(ingredient);
            Log.d("b", "" + ingredientList.size());
        } else {
            dbRef.child("pantry").child(uname).child("" + index).child(
                    "quantity").setValue(ingredient.getQuantity() + value);
            ingredient.setQuantity(ingredient.getQuantity() + value);
        }
    }
    public void updateIngredient(Ingredient ingredient, int value, Boolean t) {
        int index = ingredient.getIndex();
        if (ingredient.getQuantity() + value <= 0) {
            ingredientList.remove(ingredient);
        } else {
            ingredient.setQuantity(ingredient.getQuantity() + value);
        }
    }


    public void addRecipe(RecipeBuilder recipe) {
        ArrayList<RecipeComponent> recipeItems = recipe.recipeToArray();
        for (RecipeComponent item: recipeItems) {
            dbRef.child("recipes").child(recipe.getName()).child(item.getName()).setValue(
                    item.getQuantity());
        }
        recipeList.add(recipe);
    }
    public void updateIngredientShoppingList(Ingredient ingredient, int value) {
        int index = ingredient.getIndex();
        if (ingredient.getQuantity() + value <= 0 || value == 0) {
            dbRef.child("shoppinglist").child(uname).child("" + index).removeValue();
            Log.d("a", "" + ingredientList.size());
            shoppingList.remove(ingredient);
            Log.d("b", "" + ingredientList.size());
        } else {
            dbRef.child("shoppinglist").child(uname).child("" + index).child(
                    "quantity").setValue(ingredient.getQuantity() + value);
            ingredient.setQuantity(ingredient.getQuantity() + value);
        }
    }

    public void buyIngredient(Ingredient ingredient) {
        updateIngredientShoppingList(ingredient, 0);
        for (Ingredient pantryIngredient : ingredientList) {
            if (pantryIngredient.getIngredientName().equals(ingredient.getIngredientName())) {
                updateIngredient(pantryIngredient, ingredient.getQuantity());
                return;
            }
        }
        ingredient.setBuying(false);
        addIngredient(ingredient);

    }            

    public boolean checkRecipe(RecipeBuilder recipe) {
        for (RecipeComponent recipeItem : recipe.recipeToArray()) {
            boolean itemValid = false;
            for (Ingredient  ingredient : ingredientList) {
                if (ingredient.getIngredientName().equals(recipeItem.getName())) {
                    if (ingredient.getQuantity() >= recipeItem.getQuantity()) {
                        itemValid = true;
                        break;
                    }
                }
            }
            if (!itemValid) {
                return false;
            }
        }
        return true;
    }
    public String getUname() {
        return uname;
    }
    public String getProfGender() {
        Log.d("h", "h");
        return profile.getGender();
    }
    public void setProfGender(String inputGender) {
        profile.setGender(inputGender);
        dbRef.child("profile").child(uname).child("gender").setValue(
                inputGender);
    }
    public int getProfHeight() {
        return profile.getHeight();
    }
    public void setProfHeight(int inputHeight) {
        profile.setHeight(inputHeight);
        dbRef.child("profile").child(uname).child("height").setValue(
                inputHeight);
    }
    public int getProfWeight() {
        return profile.getWeight();
    }
    public void setProfWeight(int inputWeight) {
        profile.setWeight(inputWeight);
        dbRef.child("profile").child(uname).child("weight").setValue(
                inputWeight);
    }

    public double getCurrDayCalorieIntake() {
        // Get current date
        Date currentDate = new Date();

        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        // Format the current date
        String formattedDate = dateFormat.format(currentDate);

        double out = 0;
        for (Meal meal : mealList) {
            if (meal.getDate().equals(formattedDate)) {
                out += meal.getCalories();
            }
        }
        return out;
    }
    public void cookRecipe(RecipeBuilder recipeBuilder) {
        int calories = 0;
        for (RecipeComponent rc : recipeBuilder.recipeToArray()) {
            Ingredient ingredient = searchIngredientList(rc.getName());
            calories += ingredient.getCalories() * rc.getQuantity();
            updateIngredient(ingredient, -rc.getQuantity());
        }
        Meal meal = new Meal(recipeBuilder.getName(), calories, (int) (0.34 * calories));
        addMeal(meal);

    }
    public void cookRecipe(RecipeBuilder recipeBuilder, Boolean t) {
        int calories = 0;
        for (RecipeComponent rc : recipeBuilder.recipeToArray()) {
            Ingredient ingredient = searchIngredientList(rc.getName());
            calories += ingredient.getCalories() * rc.getQuantity();
            updateIngredient(ingredient, -rc.getQuantity(), true);
        }
        //Meal meal = new Meal(recipeBuilder.getName(), calories, (int) (0.34 * calories));
        //addMeal(meal);

    }

    public void shopIngredients(ArrayList<RecipeComponent> recipeComponents) {
        for (RecipeComponent rc : recipeComponents) {
            Ingredient ingredient = searchIngredientList(rc.getName());
            Ingredient shoppingIngredient = searchShoppingList(rc.getName());
            if (ingredient == null) {
                if (shoppingIngredient == null) {
                    addIngredientShoppingList(new Ingredient(rc.getName(), rc.getQuantity(),
                            50, ""));
                } else {
                    if (shoppingIngredient.getQuantity() < rc.getQuantity()) {
                        updateIngredientShoppingList(shoppingIngredient, rc.getQuantity()
                                - shoppingIngredient.getQuantity());
                    }
                }
            } else if (ingredient.getQuantity() < rc.getQuantity()) {
                if (shoppingIngredient == null) {
                    addIngredientShoppingList(new Ingredient(rc.getName(), rc.getQuantity()
                            - ingredient.getQuantity(), 50, ""));
                } else {
                    if (shoppingIngredient.getQuantity()
                            + ingredient.getQuantity() < rc.getQuantity()) {
                        updateIngredientShoppingList(shoppingIngredient, rc.getQuantity()
                                - (shoppingIngredient.getQuantity() + ingredient.getQuantity()));
                    }
                }
            }
        }
    }
    public Ingredient searchIngredientList(String name) {
        for (Ingredient ingredient: ingredientList) {
            if (ingredient.getIngredientName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
    public Ingredient searchShoppingList(String name) {
        for (Ingredient ingredient: shoppingList) {
            if (ingredient.getIngredientName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
    public ArrayList<Meal> getMealList() {
        return mealList; 
    }
    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }
    public int getNextIngredientIndex() {
        return nextIngredientIndex;
    }

    public ArrayList<RecipeBuilder> getRecipeList() {
        return recipeList;
    }
    public MainActivity getActivity() {
        return activity;
    }
    public void setActivity(AppCompatActivity activity) {
        this.activity = (MainActivity) activity;
    }
    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }

}
