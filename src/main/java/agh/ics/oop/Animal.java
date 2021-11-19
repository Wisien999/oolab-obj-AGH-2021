package agh.ics.oop;

import java.text.MessageFormat;

public class Animal extends AbstractMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d previousPosition;
    private final IWorldMap map;

    Animal(IWorldMap map) {
        super(new Vector2d(2, 2));
        this.map = map;
//        this(map, new Vector2d(2, 2));
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
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
                    Vector2d prevPrevPosition = this.previousPosition;
                    this.previousPosition = position;
                    this.position = newPosition;
                    if (!this.map.place(this)) {
                        this.position = this.previousPosition;
                        this.previousPosition = prevPrevPosition;
                    }
                }
            }
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
    public Vector2d getPreviousPosition() {return previousPosition;}
}
