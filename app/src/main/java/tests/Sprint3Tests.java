package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.cs2340ateam34.Meal;
import com.example.cs2340ateam34.Profile;
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
        User a = User.getInstance("a");
        assertEquals("a", a.getUname());
    }



}