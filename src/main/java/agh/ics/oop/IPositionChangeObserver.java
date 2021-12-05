package agh.ics.oop;

public interface IPositionChangeObserver {
    boolean positionChanged(Object object, Vector2d oldPosition, Vector2d newPosition);
}
