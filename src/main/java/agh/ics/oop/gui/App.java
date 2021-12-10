package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.MapElements.AbstractWorldMapElement;
import agh.ics.oop.MapElements.IMapElement;
import agh.ics.oop.MapTypes.AbstractWorldMap;
import agh.ics.oop.MapTypes.GrassField;
import agh.ics.oop.MapTypes.IWorldMap;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class App extends Application {
    private AbstractWorldMap map;
    @Override
    public void init() throws Exception {
        super.init();

        Parameters parameters = getParameters();
        System.out.println(parameters.getRaw());
        List<String> args = parameters.getRaw();
        try {
            List<MoveDirection> directions = new OptionsParser().parse(args);
            //        IWorldMap map = new RectangularMap(10, 5);
            this.map = new GrassField(10);

            List<Vector2d> positions = Arrays.asList(new Vector2d(2,1), new Vector2d(3,4));
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();

            System.out.println(this.map);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        int cellWidth = 50;
        int cellHeight = 50;

        int minY = this.map.getLowerLeftDrawLimit().y;
        int minX = this.map.getLowerLeftDrawLimit().x;
        int maxY = this.map.getUpperRightDrawLimit().y;
        int maxX = this.map.getUpperRightDrawLimit().x;

        Label xyLabel = new Label("y\\x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
        gridPane.add(xyLabel, 0, 0, 1, 1);


        for (int i = minY; i <= maxY; i++) {
            Label label = new Label(Integer.toString(i));
            gridPane.add(label, 0, maxY - (i - minX + 1), 1, 1);
            gridPane.getRowConstraints().add(new RowConstraints(cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = minX; i <= maxX; i++) {
            Label label = new Label(Integer.toString(i));
            gridPane.add(label, i-minX+1, 0, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Vector2d position = new Vector2d(x, y);
                if (!this.map.isOccupied(position)) {
                    continue;
                }

                IMapElement worldMapElement = (IMapElement) this.map.objectAt(position);
                GuiElementBox element = new GuiElementBox(worldMapElement);
                VBox graphicalElement = element.getGraphicalElement();
                GridPane.setHalignment(graphicalElement, HPos.CENTER);
                gridPane.add(graphicalElement, position.x - minX + 1, maxY - (position.y - minY), 1, 1);
            }
        }


        Scene scene = new Scene(gridPane, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
