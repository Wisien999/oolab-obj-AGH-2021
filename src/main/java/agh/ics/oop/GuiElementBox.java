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

public class GuiElementBox {
    protected VBox graphicalElement = new VBox(4);

    public GuiElementBox(IMapElement mapElement) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(mapElement.getImageResource()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(18);
        imageView.setFitHeight(18);


        Label label = new Label(mapElement.toStringRepresentation());
        label.setFont(Font.font(10d));

        this.graphicalElement.getChildren().add(imageView);
        this.graphicalElement.getChildren().add(label);
        this.graphicalElement.setAlignment(Pos.CENTER);
    }

    public VBox getGraphicalElement() {
        return graphicalElement;
    }
}
