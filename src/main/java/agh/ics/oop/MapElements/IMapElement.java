package agh.ics.oop.MapElements;

import agh.ics.oop.Vector2d;

public interface IMapElement {
    Vector2d getPosition();
    String toString();
    String getImageResource();
    public String toStringRepresentation();
}
