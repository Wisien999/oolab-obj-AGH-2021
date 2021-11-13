package agh.ics.oop;

import java.text.MessageFormat;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d previousPosition;
    private Vector2d position = new Vector2d(2, 2);
    private final IWorldMap map;

    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }



    public void move(MoveDirection direction) {
        Vector2d orientationVector = this.orientation.toUnitVector();


        switch (direction) {
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            default:
                Vector2d newPosition = switch (direction) {
                    case FORWARD -> this.position.add(orientationVector);
                    case BACKWARD -> this.position.add(orientationVector.opposite());
                    default -> this.position;
                };

                if (this.map.canMoveTo(newPosition)) {
                    this.previousPosition = position;
                    this.position = newPosition;
                    this.map.place(this);
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
