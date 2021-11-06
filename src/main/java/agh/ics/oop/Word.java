package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Word {
    public static void main(String[] args) {
        System.out.println("\n\nstarted");
        Animal animal = new Animal();
        System.out.println(animal);

        List<String> strMoves = Arrays.asList("f", "forward", "f", "right", "f", "r", "r", "b");
        OptionsParser optionsParser = new OptionsParser();
        List<MoveDirection> moves = optionsParser.parse(Arrays.asList(args));

        for (MoveDirection move : moves) {
            System.out.println(move);
            animal.move(move);
        }

        System.out.println(animal);


        System.out.println("ended\n");
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
