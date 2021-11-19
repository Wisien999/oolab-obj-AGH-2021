package agh.ics.oop;

import java.util.Arrays;
import java.util.List;

public class Word {
    public static void main(String[] args) {
        System.out.println("\n\nstarted");

        List<MoveDirection> directions = new OptionsParser().parse(Arrays.asList(args));
//        IWorldMap map = new RectangularMap(10, 5);
        IWorldMap map = new GrassField(10);

        List<Vector2d> positions = Arrays.asList(new Vector2d(2,2), new Vector2d(3,4));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.println(map);

        System.out.println("ended\n");
    }
}
