package agh.ics.oop;

import agh.ics.oop.MapTypes.GrassField;
import agh.ics.oop.MapTypes.IWorldMap;

import java.util.Arrays;
import java.util.List;

public class Word {
    public static void main(String[] args) {
        System.out.println("\n\nstarted");

        try {
            List<MoveDirection> directions = new OptionsParser().parse(Arrays.asList(args));
    //        IWorldMap map = new RectangularMap(10, 5);
            IWorldMap map = new GrassField(10);

            List<Vector2d> positions = Arrays.asList(new Vector2d(2,1), new Vector2d(3,4));
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();

            System.out.println(map);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("ended\n");
    }
}
