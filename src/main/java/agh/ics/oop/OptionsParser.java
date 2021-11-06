package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsParser {
    public List<MoveDirection> parse(List<String> strMoves) {

        ArrayList<String> possibleMoves = new ArrayList<>(Arrays.asList("f", "forward", "b", "backward", "r", "right", "l", "left"));
        List<MoveDirection> moves = strMoves.stream()
                .filter(possibleMoves::contains)
                .map(move -> switch (move) {
                    case "f", "forward" -> MoveDirection.FORWARD;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "l", "left" -> MoveDirection.LEFT;
                    default -> throw new IllegalStateException("Unexpected value"); // It won't happen but java for some reason thinks it can happen
                })
                .collect(Collectors.toList());

        return moves;
    }
}
