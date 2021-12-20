package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.MapElements.IMapElement;
import agh.ics.oop.MapTypes.AbstractWorldMap;
import agh.ics.oop.MapTypes.GrassField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class App extends Application implements IMapRefreshNeededObserver {
    private AbstractWorldMap map;
    private ThreadedSimulationEngine engine;
    private Thread engineThread;
    private GridPane mapGrid;

    @Override
    public void init() throws Exception {
        super.init();

        Parameters parameters = getParameters();
        System.out.println(parameters.getRaw());
        List<String> args = parameters.getRaw();
        try {
//            List<MoveDirection> directions = new OptionsParser().parse(args);
            //        IWorldMap map = new RectangularMap(10, 5);
            this.map = new GrassField(10);

            List<Vector2d> positions = Arrays.asList(new Vector2d(2,1), new Vector2d(3,4));
            this.engine = new ThreadedSimulationEngine(map, positions, 3000);
            this.engine.addObserver(this);

            System.out.println(this.map);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        this.mapGrid = new GridPane();
        mapGrid.setGridLinesVisible(true);
        mapGrid.setHgap(0);
        mapGrid.setVgap(0);

        VBox root = new VBox();
        TextField textField = new TextField();
        Button button = new Button("Start");
        button.setOnAction(event -> {
            List<String> strMoves = Arrays.asList(textField.getCharacters().toString().split(" "));

            List<MoveDirection> moves = new OptionsParser().parse(strMoves);
            this.engine.setMoves(moves);
            this.engineThread = new Thread(this.engine);
            this.engineThread.start();
        });


        root.getChildren().addAll(textField, button);


        root.getChildren().add(mapGrid);

        renderGrid(mapGrid);

//        Platform.runLater(() -> {
//            renderGridAndQueueNextRender(gridPane, cellWidth, cellHeight);
//        });

        Scene scene = new Scene(root, 600, 600);
//        primaryStage.getScene().getRoot().

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void renderGridAndQueueNextRender(GridPane gridPane, int cellWidth, int cellHeight) {
        gridPane.getChildren().clear();
        System.out.println(gridPane.getChildren());
        renderGrid(gridPane);

//        Platform.runLater(() -> {
//            renderGridAndQueueNextRender(gridPane, cellWidth, cellHeight);
//
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    }

    void renderGrid(GridPane gridPane) {
        int minY = this.map.getLowerLeftDrawLimit().y;
        int minX = this.map.getLowerLeftDrawLimit().x;
        int maxY = this.map.getUpperRightDrawLimit().y;
        int maxX = this.map.getUpperRightDrawLimit().x;

        Label xyLabel = new Label("y\\x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(GUIConfig.mapGridCellWidth));
        gridPane.getRowConstraints().add(new RowConstraints(GUIConfig.mapGridCellHeight));
        gridPane.add(xyLabel, 0, 0, 1, 1);


        for (int i = minY; i <= maxY; i++) {
            Label label = new Label(Integer.toString(i));
            // n = maxY - minY + 1
            // n - (i - minY) = max - min + 1 -i + max = 2max -min- i + 1
            gridPane.add(label, 0, maxY - i + 1, 1, 1);
            gridPane.getRowConstraints().add(new RowConstraints(GUIConfig.mapGridCellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = minX; i <= maxX; i++) {
            Label label = new Label(Integer.toString(i));
            gridPane.add(label, i-minX+1, 0, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(GUIConfig.mapGridCellWidth));
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
                gridPane.add(graphicalElement, position.x - minX + 1, maxY - position.y + 1, 1, 1);
            }
        }
    }

    @Override
    public void refresh() {
        Platform.runLater(() -> {
            this.mapGrid.getChildren().clear();
            this.renderGrid(this.mapGrid);
        });
    }
}
