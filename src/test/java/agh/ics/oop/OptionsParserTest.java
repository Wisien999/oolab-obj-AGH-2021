package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class OptionsParserTest {
    private OptionsParser optionsParser = new OptionsParser();

    @Test
    public void parseTest() {
        List<String> strMoves1 = Arrays.asList("f", "forward", "f", "right", "f", "r", "r", "b");
        List<MoveDirection> moves1 = Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.BACKWARD);
        assertEquals(moves1, this.optionsParser.parse(strMoves1));

        List<String> strMoves2 = Arrays.asList("f", "forward", "ffa", "right", "f", "l", "left", "backward");
        List<MoveDirection> moves2 = Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD);
        assertEquals(moves2, this.optionsParser.parse(strMoves2));

        List<String> strMoves3 = Arrays.asList("b", "ffa", "right", "f", "l", "left", "backward");
        List<MoveDirection> moves3 = Arrays.asList(MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD);
        assertEquals(moves3, this.optionsParser.parse(strMoves3));

        List<String> strMoves4 = Arrays.asList("b");
        List<MoveDirection> moves4 = Arrays.asList(MoveDirection.BACKWARD);
        assertEquals(moves4, this.optionsParser.parse(strMoves4));
    }
}
