package agh.ics.oop;

import agh.ics.oop.MapElements.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox implements IPositionChangeObserver {
    protected IMapElement mapElement;
    protected VBox graphicalElement = new VBox(4);
    protected Label label;

    public GuiElementBox(IMapElement mapElement) throws FileNotFoundException {
        this.mapElement = mapElement;
        Image image = new Image(new FileInputStream(this.mapElement.getImageResource()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(18);
        imageView.setFitHeight(18);


        this.label = new Label(this.mapElement.toStringRepresentation());
        label.setFont(Font.font(10d));

        this.graphicalElement.getChildren().add(imageView);
        this.graphicalElement.getChildren().add(label);
        this.graphicalElement.setAlignment(Pos.CENTER);
    }

    public VBox getGraphicalElement() {
        return graphicalElement;
    }

    @Override
    public boolean positionChanged(Object object, Vector2d oldPosition, Vector2d newPosition) {
        label.setText(this.mapElement.toStringRepresentation());
        this.getGraphicalElement().getChildren().add(label);
        return false;
    }
}
