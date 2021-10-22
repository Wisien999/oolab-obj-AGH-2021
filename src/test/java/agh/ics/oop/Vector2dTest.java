package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Vector2dTest {
    Vector2d vector1;
    Vector2d vector2;
    Vector2d vector3;
    Vector2d vector4;
    Vector2d vector5;

    @BeforeEach
    private void setup() {
        vector1 = new Vector2d(1, 5);
        vector2 = new Vector2d(3, -2);
        vector3 = new Vector2d(1, 5);
        vector4 = new Vector2d(0, 0);
        vector5 = new Vector2d(3, 0);
    }

    @Test
    public void equalsTest() {
        assertEquals(vector1, vector1);
        assertEquals(vector1, vector3);
        assertNotEquals(vector2, vector1);
        assertNotEquals(null, vector2);
    }

    @Test
    public void toStringTest() {
        assertEquals("(3, -2)", vector2.toString());
        assertEquals("(0, 0)", vector4.toString());
    }

    @Test
    public void precedesTest() {
        assertFalse(vector2.precedes(vector4));
        assertFalse(vector2.precedes(vector3));
        assertTrue(vector2.precedes(vector2));
        assertTrue(vector3.precedes(vector3));
        assertFalse(vector1.precedes(vector2));
        assertTrue(vector1.precedes(vector3));
    }

    @Test
    public void followsTest() {
        assertFalse(vector2.follows(vector5));
        assertFalse(vector2.follows(vector3));
        assertTrue(vector2.follows(vector2));
        assertTrue(vector3.follows(vector3));
        assertFalse(vector1.follows(vector2));
        assertTrue(vector1.follows(vector3));
    }

    @Test
    public void upperRightTest() {
        assertEquals(new Vector2d(1, 5), vector4.upperRight(vector3));
        assertEquals(new Vector2d(3, 5), vector1.upperRight(vector2));
        assertEquals(new Vector2d(3, 5), vector5.upperRight(vector3));
    }

    @Test
    public void lowerLeftTest() {
        assertEquals(new Vector2d(0, 0), vector4.lowerLeft(vector3));
        assertEquals(new Vector2d(1, -2), vector1.lowerLeft(vector2));
        assertEquals(new Vector2d(1, 0), vector5.lowerLeft(vector3));
    }

    @Test
    public void addTest() {
        assertEquals(new Vector2d(4, 3), vector1.add(vector2));
        assertEquals(new Vector2d(4, 3), vector2.add(vector1));
        assertEquals(new Vector2d(3, -2), vector2.add(vector4));
        assertEquals(new Vector2d(4, 5), vector3.add(vector5));
    }

    @Test
    public void subtractTest() {
        assertEquals(new Vector2d(-2, 7), vector1.subtract(vector2));
        assertEquals(new Vector2d(2, -7), vector2.subtract(vector1));
        assertEquals(new Vector2d(3, -2), vector2.subtract(vector4));
        assertEquals(new Vector2d(-2, 5), vector3.subtract(vector5));
    }

    @Test
    public void oppositeTest() {
        assertEquals(new Vector2d(-1, -5), vector1.opposite());
        assertEquals(new Vector2d(-3, 2), vector2.opposite());
        assertEquals(new Vector2d(-1, -5), vector3.opposite());
        assertEquals(new Vector2d(-0, -0), vector4.opposite());
        assertEquals(new Vector2d(-3, -0), vector5.opposite());
    }
}
