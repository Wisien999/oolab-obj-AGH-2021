package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    public void animalTest() {
        Animal animal = new Animal();
        OptionsParser optionsParser = new OptionsParser();
        List<String> strMoves;
        List<MoveDirection> moves;

        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());

        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());

        strMoves = Arrays.asList("f", "forward");
        moves = optionsParser.parse(strMoves);
        for (MoveDirection move : moves) {
            animal.move(move);
        }
        assertEquals(new Vector2d(2, 4), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());

        animal.move(MoveDirection.LEFT);
        assertEquals(new Vector2d(2, 4), animal.getPosition());
        assertEquals(MapDirection.WEST, animal.getOrientation());

        strMoves = Arrays.asList("f", "forward", "r", "err", "right");
        moves = optionsParser.parse(strMoves);
        for (MoveDirection move : moves) {
            animal.move(move);
        }
        assertEquals(new Vector2d(0, 4), animal.getPosition());
        assertEquals(MapDirection.EAST, animal.getOrientation());

        strMoves = Arrays.asList("f", "r", "forward", "forward", "forward");
        moves = optionsParser.parse(strMoves);
        for (MoveDirection move : moves) {
            animal.move(move);
        }
        assertEquals(new Vector2d(1, 1), animal.getPosition());
        assertEquals(MapDirection.SOUTH, animal.getOrientation());

        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(1, 2), animal.getPosition());
        assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }
}
