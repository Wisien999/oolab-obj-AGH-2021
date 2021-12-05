package agh.ics.oop.MapElements;

import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractMovableWorldMapElement extends AbstractWorldMapElement {
    private final List<IPositionChangeObserver> positionObservers = new ArrayList<>();

    public AbstractMovableWorldMapElement(Vector2d position) {
        super(position);
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.positionObservers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.positionObservers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : this.positionObservers) {
            observer.positionChanged(this, oldPosition, newPosition);
        }
    }

}
