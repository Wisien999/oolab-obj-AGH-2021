package agh.ics.oop;

import agh.ics.oop.MapElements.AbstractMovableWorldMapElement;
import agh.ics.oop.MapElements.AbstractWorldMapElement;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver {
    SortedSet<MapBoundarySetElement> xSortedElements = new TreeSet<>(new MapBoundarySetElementPrimaryXPosComparator());
    SortedSet<MapBoundarySetElement> ySortedElements = new TreeSet<>(new MapBoundarySetElementPrimaryYPosComparator());

    public void addWorldMapElement(AbstractMovableWorldMapElement worldMapElement) {
        worldMapElement.addObserver(this);

        MapBoundarySetElement setElement = new MapBoundarySetElement(worldMapElement.getPosition(), worldMapElement);
        this.xSortedElements.add(setElement);
        this.ySortedElements.add(setElement);
    }

    public Vector2d getLowerLeftCorner() {
        return this.xSortedElements.first().getPosition().lowerLeft(ySortedElements.first().getPosition());
    }
    public Vector2d getUpperRightCorner() {
        return this.xSortedElements.last().getPosition().upperRight(ySortedElements.last().getPosition());
    }

    @Override
    public boolean positionChanged(Object object, Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement worldMapElement = (AbstractWorldMapElement) object;

        var oldSetObject = new MapBoundarySetElement(oldPosition, worldMapElement);
        this.xSortedElements.remove(oldSetObject);
        this.ySortedElements.remove(oldSetObject);

        var newSetObject = new MapBoundarySetElement(newPosition, worldMapElement);
        this.xSortedElements.add(newSetObject);
        this.ySortedElements.add(newSetObject);

        return true;
    }
}
