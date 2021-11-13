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

        List<MoveDirection> directions = new OptionsParser().parse(Arrays.asList(args));
        IWorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2,2), new Vector2d(3,4));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        MapVisualizer mapVisualizer = new MapVisualizer(map);
        System.out.println(mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(9, 4)));

        System.out.println("ended\n");
    }

    static void run(List<Direction> args) {
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
