package agh.ics.oop;

import java.text.MessageFormat;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);


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

                if (newPosition.precedes(new Vector2d(4, 4)) && newPosition.follows(new Vector2d(0, 0))) {
                    this.position = newPosition;
                }
        }
    }

    public String toString() {
        return MessageFormat.format("Animal at position {0}, orientated {1}", this.position.toString(), this.orientation.toString());
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }
}
