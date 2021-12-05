package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsParser {
    public List<MoveDirection> parse(List<String> strMoves) {

        ArrayList<String> possibleMoves = new ArrayList<>(Arrays.asList("f", "forward", "b", "backward", "r", "right", "l", "left"));
        List<MoveDirection> moves = strMoves.stream()
                .map(move -> switch (move) {
                    case "f", "forward" -> MoveDirection.FORWARD;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "l", "left" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(move + " is not legal move specification");
                })
                .collect(Collectors.toList());

        return moves;
    }
}
