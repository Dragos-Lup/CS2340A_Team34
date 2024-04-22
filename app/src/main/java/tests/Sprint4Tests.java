package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.cs2340ateam34.Ingredient;
import com.example.cs2340ateam34.RecipeBase;
import com.example.cs2340ateam34.RecipeBuilder;
import com.example.cs2340ateam34.RecipeComponent;
import com.example.cs2340ateam34.RecipeDecorator;
import com.example.cs2340ateam34.User;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Tests
 *
 */
public class Sprint4Tests {
    @Test
    public void userTesting() {
        User a = User.getInstance();
        assertNull(a.getUname());
    }
    @Test
    public void shoppingTesting() {
        User a = User.getInstance();
        assertEquals(0, a.getShoppingList().size());
    }

    @Test
    public void addTesting() {
        User a = User.getInstance();
        Ingredient i = new Ingredient("Egg", 1, 2, "Never");
        a.addIngredientShoppingList(i, true);

        assertEquals("Egg", a.getShoppingList().get(0).getIngredientName());
    }

    @Test
    public void recipeBuilderAddTesting() {
        RecipeBuilder rb = new RecipeBuilder("cake");
        rb.addComponent("eggs", 14);
        assertEquals(1, rb.recipeToArray().size());
        rb.addComponent("milk", 2);
        assertEquals(2, rb.recipeToArray().size());
    }

    @Test
    public void recipeBuilderOrderTesting() {
        RecipeBuilder rb = new RecipeBuilder("cake");
        rb.addComponent("eggs", 14);
        assertEquals("eggs", rb.recipeToArray().get(0).getName());
        rb.addComponent("milk", 2);
        assertEquals("eggs", rb.recipeToArray().get(0).getName());
        assertEquals("milk", rb.recipeToArray().get(1).getName());
    }

    @Test
    public void recipeBaseTesting() {
        RecipeBase rb = new RecipeBase("eggs", 3);
        ArrayList<RecipeComponent> rtn = new ArrayList<>();
        rb.retrieveComponent(rtn);
        assertEquals("eggs", rtn.get(0).getName());
    }
    @Test
    public void recipeDecoratorTesting() {
        RecipeBase rb = new RecipeBase("eggs", 3);
        RecipeDecorator rd = new RecipeDecorator("milk", rb, 4);
        ArrayList<RecipeComponent> rtn = new ArrayList<>();
        rd.retrieveComponent(rtn);
        assertEquals("eggs", rtn.get(0).getName());
        assertEquals("milk", rtn.get(1).getName());
    }

    @Test
    public void recipeCheckingTesting() {
        RecipeBuilder rb = new RecipeBuilder("cake");
        rb.addComponent("eggs", 1);
        User a = User.getInstance();
        assertFalse(a.checkRecipe(rb));
        Ingredient i = new Ingredient("eggs", 1, 40, "never");
        a.addIngredient(i, true);
        assertTrue(a.checkRecipe(rb));

    }
    @Test
    public void recipeCheckingTesting2() {
        RecipeBuilder rb = new RecipeBuilder("alien food");
        rb.addComponent("rocks", 4);
        rb.addComponent("light", 1);
        User a = User.getInstance();
        assertFalse(a.checkRecipe(rb));
        Ingredient e = new Ingredient("rocks", 1, 40, "never");
        Ingredient m = new Ingredient("light", 1, 40, "never");
        a.addIngredient(e, true);
        assertFalse(a.checkRecipe(rb));
        a.addIngredient(m, true);
        assertFalse(a.checkRecipe(rb));
        a.updateIngredient(e, 5, true);
        assertTrue(a.checkRecipe(rb));
    }
    @Test
    public void cookingTesting() {
        RecipeBuilder rb = new RecipeBuilder("salad");
        rb.addComponent("steak", 4);
        rb.addComponent("grass", 1);
        User a = User.getInstance();
        Ingredient e = new Ingredient("grass", 1, 40, "never");
        Ingredient m = new Ingredient("steak", 1, 40, "never");
        a.addIngredient(e, true);
        a.updateIngredient(e, 5, true);
        a.addIngredient(m, true);
        a.updateIngredient(m, 5, true);
        a.cookRecipe(rb, true);
        assertEquals(2, a.searchIngredientList("steak").getQuantity());
        assertEquals(5, a.searchIngredientList("grass").getQuantity());
    }
}