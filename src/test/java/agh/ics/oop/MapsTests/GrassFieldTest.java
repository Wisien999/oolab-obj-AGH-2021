package agh.ics.oop.MapsTests;

import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapTypes.GrassField;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    public void drawLimitsTest() {
        GrassField grassField = new GrassField(0);

        Assertions.assertEquals(new Vector2d(0, 0), grassField.getLowerLeftDrawLimit());
        Assertions.assertEquals(new Vector2d(0, 0), grassField.getUpperRightDrawLimit());

        grassField.place(new Animal(grassField));
        Assertions.assertEquals(new Vector2d(2, 2), grassField.getLowerLeftDrawLimit());
        Assertions.assertEquals(new Vector2d(2, 2), grassField.getUpperRightDrawLimit());

        grassField.place(new Animal(grassField, new Vector2d(12,43)));
        Assertions.assertEquals(new Vector2d(2, 2), grassField.getLowerLeftDrawLimit());
        Assertions.assertEquals(new Vector2d(12, 43), grassField.getUpperRightDrawLimit());

        grassField.place(new Animal(grassField, new Vector2d(0,33)));
        Assertions.assertEquals(new Vector2d(0, 2), grassField.getLowerLeftDrawLimit());
        Assertions.assertEquals(new Vector2d(12, 43), grassField.getUpperRightDrawLimit());
    }


    @Test
    public void isOccupiedTest() {
        GrassField grassField = new GrassField(0);


        Vector2d pos1 = new Vector2d(4, 2);
        Assertions.assertFalse(grassField.isOccupied(pos1));

        grassField.place(new Animal(grassField, pos1));
        Assertions.assertTrue(grassField.isOccupied(pos1));
    }

    @Test
    public void placeTest() {
        GrassField grassField = new GrassField(6);

        Vector2d pos1 = new Vector2d(3,5);
        Vector2d pos2 = new Vector2d(3,5);
        Vector2d pos3 = new Vector2d(5,4);
        Vector2d pos4 = new Vector2d(10,-9);

        Animal animal1 = new Animal(grassField, pos1);

        Assertions.assertFalse(grassField.place(null));
        Assertions.assertTrue(grassField.place(animal1));
        Assertions.assertFalse(grassField.place(new Animal(grassField, pos2)));
        Assertions.assertTrue(grassField.place(new Animal(grassField, pos3)));
        Assertions.assertTrue(grassField.place(new Animal(grassField, pos4)));
        Assertions.assertFalse(grassField.place(new Animal(grassField, null)));
    }

    @Test
    public void canMoveToTest() {
        GrassField grassField = new GrassField(12);

        Vector2d pos1 = new Vector2d(3,5);

        Animal animal1 = new Animal(grassField, pos1);

        Assertions.assertFalse(grassField.canMoveTo(null));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(45,2)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(21,2)));

        grassField.place(animal1);
        Assertions.assertFalse(grassField.canMoveTo(pos1));
    }
}
