package agh.ics.oop;

import agh.ics.oop.MapTypes.IWorldMap;
import agh.ics.oop.MapTypes.RectangularMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class SimulationTest {
    @Test
    public void test1() {
        IWorldMap worldMap = new RectangularMap(3, 4);
        List<Vector2d> animalsInitialPositions = Arrays.asList(new Vector2d(1, 0), new Vector2d(2, 2), new Vector2d(0, 0));
        List<MoveDirection> moves = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD);

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, animalsInitialPositions);
        simulationEngine.run();

        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);
        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(2, 3));
        System.out.println(result);
        String expectedRes =
                        " y\\x  0 1 2\n" +
                        "  4: -------\n" +
                        "  3: | | | |\n" +
                        "  2: | | |E|\n" +
                        "  1: | |W| |\n" +
                        "  0: |N| | |\n" +
                        " -1: -------\n";

        Assertions.assertEquals(expectedRes, result);
    }

    @Test
    public void test2() {
        IWorldMap worldMap = new RectangularMap(7, 5);
        List<Vector2d> animalsInitialPositions = Arrays.asList(new Vector2d(1, 0), new Vector2d(2, 2));
        List<MoveDirection> moves = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD);

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, animalsInitialPositions);
        simulationEngine.run();

        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);
        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(6, 4));
        System.out.println(result);
        String expectedRes =
                " y\\x  0 1 2 3 4 5 6\n" +
                "  5: ---------------\n" +
                "  4: | | | | | | | |\n" +
                "  3: | | | | | | | |\n" +
                "  2: | |E| | | | | |\n" +
                "  1: |W| | | | | | |\n" +
                "  0: | | | | | | | |\n" +
                " -1: ---------------\n";

        Assertions.assertEquals(expectedRes, result);
    }

    @Test
    public void test3() {
        IWorldMap worldMap = new RectangularMap(7, 5);
        List<Vector2d> animalsInitialPositions = Arrays.asList(new Vector2d(1, 0), new Vector2d(2, 2));
        List<MoveDirection> moves = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD);

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, animalsInitialPositions);
        simulationEngine.run();

        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);
        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(6, 4));
        System.out.println(result);
        String expectedRes =
                " y\\x  0 1 2 3 4 5 6\n" +
                "  5: ---------------\n" +
                "  4: | | | | | | | |\n" +
                "  3: | | | | | | | |\n" +
                "  2: | | | | |E| | |\n" +
                "  1: |W| | | | | | |\n" +
                "  0: | | | | | | | |\n" +
                " -1: ---------------\n";

        Assertions.assertEquals(expectedRes, result);
    }

    @Test
    public void test4() {
        IWorldMap worldMap = new RectangularMap(10, 5);
        List<Vector2d> animalsInitialPositions = Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> moves = Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD);

        IEngine simulationEngine = new SimulationEngine(moves, worldMap, animalsInitialPositions);
        simulationEngine.run();

        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);
        String result = mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(9, 4));
        String expectedRes =
                " y\\x  0 1 2 3 4 5 6 7 8 9\n" +
                "  5: ---------------------\n" +
                "  4: | | | |N| | | | | | |\n" +
                "  3: | | | | | | | | | | |\n" +
                "  2: | | | | | | | | | | |\n" +
                "  1: | | | | | | | | | | |\n" +
                "  0: | | |S| | | | | | | |\n" +
                " -1: ---------------------\n";

        Assertions.assertEquals(expectedRes, result);
    }
}
