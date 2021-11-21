package agh.ics.oop.MapElements;

import agh.ics.oop.Vector2d;

public class Grass extends AbstractWorldMapElement {
    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    public String toString() {
        return "*";
    }
}
