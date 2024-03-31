package tests;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340ateam34.Meal;
import com.example.cs2340ateam34.Profile;
import com.example.cs2340ateam34.User;

import java.util.ArrayList;

/**
 * Tests
 *
 */
public class ExampleUnitTest {
    @Test
    public void mealTesting1() {
        String name = "Coookies";
        String date = "Last Week";
        int calories = 230430;
        int price = 5;
        Meal meal = new Meal(name, calories, price, date);
        assertEquals(meal.getMealName(), name);
        assertEquals(meal.getCalories(), calories);
        assertEquals(meal.getPrice(), price);
        assertEquals(meal.getDate(), date);

    }
/*
    @Test
    public void mealTesting2() {
        String name = "Cookies";
        String date = "Last Week";
        int calories = 230430;
        int price = 5;
        Meal meal = new Meal("Cookies", 230430, 5, "Last Week");
        assertEquals(meal.getMealName(), name);
        assertEquals(meal.getCalories(), calories);
        assertEquals(meal.getPrice(), price);
        assertEquals(meal.getDate(), date);

    }
*/
    @Test
    public void mealTesting3() {
        Meal meal = new Meal("Cookies", 230430, 5, "Last Week");
        assertEquals(meal.getMealName(), "Cookies");
        assertEquals(meal.getCalories(), 230430);
        assertEquals(meal.getPrice(), 5);
        assertEquals(meal.getDate(), "Last Week");
        meal.setMealName("Cake");
        meal.setCalories(40);
        meal.setPrice(12345);
        meal.setDate("Tommorow");
        assertEquals(meal.getMealName(), "Cake");
        assertEquals(meal.getCalories(), 40);
        assertEquals(meal.getPrice(), 12345);
        assertEquals(meal.getDate(), "Tommorow");
    }

    @Test
    public void userTesting1() {
        User a = User.getInstance();
        assertNull(a.getUname());
        assertEquals(new ArrayList<>(), a.getMealList());
    }

    @Test
    public void userTesting2() {
        User a = User.getInstance("a");
        assertEquals("a", a.getUname());
        Meal meal = new Meal("Cookies", 230430, 5, "Last Week");
        a.addMeal(meal);
        assertEquals(meal, a.getMealList().get(a.getMealList().size() - 1));
    }

    @Test
    public void profileTesting1() {
        Profile prof = new Profile(2, 2000, "male");
        assertEquals(2, prof.getHeight());
        assertEquals(2000, prof.getWeight());
        assertEquals("male", prof.getGender());
    }

    @Test
    public void profileTesting2() {
        Profile prof = new Profile(2, 2000, "male");
        prof.setGender("female");
        prof.setHeight(30);
        prof.setWeight(1);
        assertEquals(30, prof.getHeight());
        assertEquals(1, prof.getWeight());
        assertEquals("female", prof.getGender());
    }

    @Test
    public void profileTesting3() {
        Profile prof = new Profile(2, 2000, "male");
        prof.setGender("female");
        assertEquals("female", prof.getGender());
        prof.setGender("other");
        assertEquals("other", prof.getGender());
        prof.setGender("else");
        assertEquals("else", prof.getGender());
    }

}