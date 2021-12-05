package agh.ics.oop;

import agh.ics.oop.MapElements.AbstractWorldMapElement;

public class MapBoundarySetElement {
    protected Vector2d position;
    protected AbstractWorldMapElement worldMapElement;

    MapBoundarySetElement(Vector2d position, AbstractWorldMapElement worldMapElement) {
        this.position = position;
        this.worldMapElement = worldMapElement;
    }

    public AbstractWorldMapElement getWorldMapElement() {
        return worldMapElement;
    }

    public Vector2d getPosition() {
        return position;
    }
}
