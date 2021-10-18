package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Word {
    public static void main(String[] args) {
        System.out.println("program started");
//
////    // iterowanie przez pętlę
////        ArrayList<Direction> moves = new ArrayList<>();
//////        Direction[] moves = new Direction[args.length];
////
////        for (int i = 0; i < args.length; i++) {
////            switch (args[i]) {
////                case "f":
////                    moves.add(Direction.FORWARD);
//////                    moves[i] = Direction.FORWARD;
////                    break;
////                case "b":
////                    moves.add(Direction.BACKWARD);
//////                    moves[i] = Direction.BACKWARD;
////                    break;
////                case "r":
////                    moves.add(Direction.RIGHT);
//////                    moves[i] = Direction.RIGHT;
////                    break;
////                case "l":
////                    moves.add(Direction.LEFT);
//////                    moves[i] = Direction.LEFT;
////                    break;
////            }
////        }
//
////        Iterowanie przez stream
//        ArrayList<String> possibleMoves = new ArrayList<>(Arrays.asList("f", "b", "r", "l"));
//        List<Direction> moves = Arrays.stream(args)
//                .filter(possibleMoves::contains)
//                .map(move -> switch (move) {
//                    case "f" -> Direction.FORWARD;
//                    case "b" -> Direction.BACKWARD;
//                    case "r" -> Direction.RIGHT;
//                    case "l" -> Direction.LEFT;
//                    default -> throw new IllegalStateException("Unexpected value"); // It won't happen but java for some reason thinks it can happen
//                })
//                .collect(Collectors.toList());
//
//        run(moves);

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println("program ended");
    }

    static void run(List<Direction> args) {
//        System.out.println("the pet goes forward");

//        for (int i = 0; i < args.size(); i++) {
//            System.out.print(args[i]);
//            if (i < args.size() - 1) System.out.print(", ");
//        }
//        System.out.println();


        for (Direction move : args) {
            switch (move) {
                case FORWARD:
                    System.out.println("Do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Do tyłu");
                    break;
                case RIGHT:
                    System.out.println("w prawo");
                    break;
                case LEFT:
                    System.out.println("w lewo");
                    break;
            }
        }

    }
}
