package agh.ics.oop.MapElements;

import agh.ics.oop.MapElements.AbstractMapElement;
import agh.ics.oop.Vector2d;

public class Grass extends AbstractMapElement {
    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    public String toString() {
        return "*";
    }
}
