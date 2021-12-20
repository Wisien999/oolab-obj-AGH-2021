package agh.ics.oop.gui;

import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapElements.Grass;
import agh.ics.oop.MapElements.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static agh.ics.oop.gui.Helpers.generateSolidColorImage;

public class GuiElementBox {
    protected IMapElement mapElement;
    protected VBox graphicalElement = new VBox(4);
    protected Label label;

    public GuiElementBox(IMapElement mapElement) {
        this.mapElement = mapElement;
        Image image;
        try {
            image = ImageLoader.getImage(this.mapElement.getImageResource());
        }
        catch (FileNotFoundException e) {
            double r = 0, g = 0, b = 0;
            if (mapElement instanceof Animal) {
                r = 0.8;
                g = 0.2;
                b = 0.6;
            }
            else if (mapElement instanceof Grass) {
                r = 0;
                g = 1;
                b = 0.1;
            }
            image = generateSolidColorImage(1, 1, r, g, b, 1);
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(18);
        imageView.setFitHeight(18);


        this.label = new Label(this.mapElement.toStringRepresentation());
        this.label.setFont(Font.font(10d));

        this.graphicalElement.getChildren().add(imageView);
        this.graphicalElement.getChildren().add(label);
        this.graphicalElement.setAlignment(Pos.CENTER);
    }

    public VBox getGraphicalElement() {
        return graphicalElement;
    }

//    @Override
//    public boolean positionChanged(Object object, Vector2d oldPosition, Vector2d newPosition) {
//        label.setText(this.mapElement.toStringRepresentation());
//        this.getGraphicalElement().getChildren().add(label);
//        return false;
//    }
}
