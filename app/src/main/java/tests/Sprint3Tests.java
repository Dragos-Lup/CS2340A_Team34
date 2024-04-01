package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.cs2340ateam34.Ingredient;
//import com.example.cs2340ateam34.Meal;
//import com.example.cs2340ateam34.Profile;
import com.example.cs2340ateam34.Recipe;
import com.example.cs2340ateam34.RecipeItem;
import com.example.cs2340ateam34.User;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Tests
 *
 */
public class Sprint3Tests {
    @Test
    public void userTesting() {
        User a = User.getInstance();
        assertNull(a.getUname());
    }

    @Test
    public void ingredientTesting1() {
        Ingredient i = new Ingredient("Egg", 1, 2, "Never");
        assertEquals(i.getCalories(), 2);
        assertEquals(i.getQuantity(), 1);
        assertEquals(i.getExpiry(), "Never");
    }

    @Test
    public void ingredientTesting2() {
        Ingredient i = new Ingredient("Egg", 1, 2, "Never");

        i.setQuantity(3);

        assertEquals(i.getQuantity(), 3);
    }

    @Test
    public void recipeItemTesting1() {
        RecipeItem ri = new RecipeItem("egg", 3);
        assertEquals(ri.getName(), "egg");
        assertEquals(ri.getQuantity(), 3);
    }

    @Test
    public void recipeTesting1() {
        RecipeItem ri = new RecipeItem("egg", 3);
        ArrayList<RecipeItem> riList = new ArrayList<RecipeItem>();
        riList.add(ri);
        Recipe r = new Recipe("scramble", riList);
        assertEquals(r.getName(), "scramble");
        assertEquals(r.getRecipeItems().get(0).getName(), "egg");
    }

    // The next couple do not work, but the intent is there

    @Test
    public void userTestingIngredient1() {
        User a = User.getInstance("a");
        Ingredient i = new Ingredient("Egg", 1, 2, "Never");
        a.addIngredient(i);

        assertEquals(a.getIngredientList().get(0).getQuantity(), 1);

        a.updateIngredient(i, 3);

        assertEquals(a.getIngredientList().get(0).getQuantity(), 4);

    }

    /** @noinspection checkstyle:WhitespaceAfter*/
    @Test
    public void userTestingIngredient2() {
        User a = User.getInstance("a");
        Ingredient i = new Ingredient("Egg", 1, 2, "Never");
        a.addIngredient(i);

        assertEquals(a.getIngredientList().get(0).getQuantity(), 1);

        a.updateIngredient(i, -6);

        assertTrue(a.getIngredientList().isEmpty());

    }

    @Test
    public void userTestingIngredient3() {
        User a = User.getInstance("a");
        Ingredient i = new Ingredient("Egg", -4, 2, "Never");
        a.addIngredient(i);

        assertTrue(a.getIngredientList().isEmpty());
    }

    @Test
    public void userTestingIngredient4() {
        User a = User.getInstance("a");
        Ingredient i = new Ingredient("Egg", 1, 2, "Never");
        Ingredient j = new Ingredient("Cheese", 3, 5, "Never");

        a.addIngredient(i);
        a.addIngredient(i);
        a.addIngredient(j);
        // SHOULDN'T BE [i,i,j]
        ArrayList<Ingredient> l = a.getIngredientList();
        assertEquals(l.get(0).getIngredientName(), "Egg");
        assertEquals(l.get(1).getIngredientName(), "Cheese");
    }

    @Test
    public void userRecipeTesting1() {
        User a = User.getInstance("a");

        RecipeItem ri = new RecipeItem("egg", 3);
        ArrayList<RecipeItem> riList = new ArrayList<RecipeItem>();
        riList.add(ri);
        Recipe r = new Recipe("scramble", riList);

        Ingredient i = new Ingredient("egg", 3, 5, "Never");


        a.addIngredient(i);
        assertTrue(a.checkRecipe(r));

    }

    @Test
    public void userRecipeTesting2() {
        User a = User.getInstance("a");

        RecipeItem ri = new RecipeItem("egg", 3);
        ArrayList<RecipeItem> riList = new ArrayList<RecipeItem>();
        riList.add(ri);
        Recipe r = new Recipe("scramble", riList);


        assertFalse(a.checkRecipe(r));

    }

}