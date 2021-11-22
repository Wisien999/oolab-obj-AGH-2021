package agh.ics.oop.MapElements;

import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.MapDirection;
import agh.ics.oop.MapTypes.IWorldMap;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> positionObservers = new ArrayList<>();

    public Animal(IWorldMap map) {
        super(new Vector2d(2, 2));
        this.map = map;
//        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.map = map;
    }

    public void move(MoveDirection direction) {
        Vector2d orientationVector = this.orientation.toUnitVector();


        switch (direction) {
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
            default -> {
                Vector2d newPosition = switch (direction) {
                    case FORWARD -> this.position.add(orientationVector);
                    case BACKWARD -> this.position.add(orientationVector.opposite());
                    default -> this.position;
                };
                if (this.map.canMoveTo(newPosition)) {
                    this.positionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.positionObservers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.positionObservers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : this.positionObservers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
//        return MessageFormat.format("Animal at position {0}, orientated {1}", this.position.toString(), this.orientation.toString());
    }

    public MapDirection getOrientation() {return orientation;}
    public Vector2d getPosition() {return position;}
}
