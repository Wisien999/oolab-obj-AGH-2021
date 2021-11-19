package agh.ics.oop.MapsTests;

import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapTypes.RectangularMap;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RectangularMapTest {
    @Test
    public void defaultRectangularMapTest() {
        RectangularMap rectangularMap = new RectangularMap();

        Assertions.assertEquals(new Vector2d(0, 0), rectangularMap.getLowerLeftDrawLimit());
        Assertions.assertEquals(new Vector2d(4, 4), rectangularMap.getUpperRightDrawLimit());
    }

    @Test
    public void customRectangularMapTest() {
        RectangularMap rectangularMap = new RectangularMap(65,23);

        Assertions.assertEquals(new Vector2d(0, 0), rectangularMap.getLowerLeftDrawLimit());
        Assertions.assertEquals(new Vector2d(64, 22), rectangularMap.getUpperRightDrawLimit());
    }

    @Test
    public void badRectangularMapTest() {
        RectangularMap rectangularMap = new RectangularMap(-65,23);

        Assertions.assertEquals(new Vector2d(0, 0), rectangularMap.getLowerLeftDrawLimit());
        Assertions.assertEquals(new Vector2d(4, 4), rectangularMap.getUpperRightDrawLimit());
    }

    @Test
    public void isOccupiedTest() {
        int width = 32;
        int height = 23;
        RectangularMap rectangularMap = new RectangularMap(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Assertions.assertFalse(rectangularMap.isOccupied(new Vector2d(x, y)));
            }
        }

        rectangularMap.place(new Animal(rectangularMap));
        Assertions.assertTrue(rectangularMap.isOccupied(new Vector2d(2, 2)));
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 2 && y == 2) continue;
                Assertions.assertFalse(rectangularMap.isOccupied(new Vector2d(x, y)));
            }
        }
    }

    @Test
    public void placeTest() {
        int width = 32;
        int height = 23;
        RectangularMap rectangularMap = new RectangularMap(width, height);

        Vector2d pos1 = new Vector2d(3,5);
        Vector2d pos2 = new Vector2d(3,5);
        Vector2d pos3 = new Vector2d(5,4);

        Animal animal1 = new Animal(rectangularMap, pos1);

        Assertions.assertFalse(rectangularMap.place(null));
        Assertions.assertTrue(rectangularMap.place(animal1));
        Assertions.assertFalse(rectangularMap.place(new Animal(rectangularMap, pos2)));
        Assertions.assertTrue(rectangularMap.place(new Animal(rectangularMap, pos3)));
        Assertions.assertFalse(rectangularMap.place(new Animal(rectangularMap, new Vector2d(width, height))));
        Assertions.assertFalse(rectangularMap.place(new Animal(rectangularMap, null)));
    }

    @Test
    public void canMoveToTest() {
        int width = 32;
        int height = 23;
        RectangularMap rectangularMap = new RectangularMap(width, height);

        Vector2d pos1 = new Vector2d(3,5);

        Animal animal1 = new Animal(rectangularMap, pos1);

        Assertions.assertFalse(rectangularMap.canMoveTo(null));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(45,2)));
        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(21,2)));

        rectangularMap.place(animal1);
        Assertions.assertFalse(rectangularMap.canMoveTo(pos1));
    }
}
