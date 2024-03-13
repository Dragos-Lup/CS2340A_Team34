package tests;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340ateam34.Meal;

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
    public void mealTesting4() {
        
    }
}