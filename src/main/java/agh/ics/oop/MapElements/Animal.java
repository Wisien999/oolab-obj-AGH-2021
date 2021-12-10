package agh.ics.oop.MapElements;

import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.MapDirection;
import agh.ics.oop.MapTypes.IWorldMap;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMovableWorldMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;

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

    public String toStringRepresentation() {
        return "A " + this.orientation.toShortString() + " " + this.position.toString();
    }

    public String toString() {
        return this.orientation.toShortString();
    }

    @Override
    public String getImageResource() {
        String basePath = PathConfig.imageBasePath;
        return basePath + switch (this.getOrientation()) {
            case NORTH -> "up.png";
            case EAST -> "right.png";
            case SOUTH -> "down.png";
            case WEST -> "left.png";
        };
    }

    public MapDirection getOrientation() {return orientation;}
    public Vector2d getPosition() {return position;}
}
