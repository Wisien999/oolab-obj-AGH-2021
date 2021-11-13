package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    private Vector2d upperRight;
    private Vector2d lowerLeft = new Vector2d(0, 0);
    private Map<Vector2d, Animal> map = new HashMap<>();

    public RectangularMap() {
        this.width = 5;
        this.height = 5;
        this.upperRight = new Vector2d(this.width, this.height);
    }

    public RectangularMap(int width, int height) {
        this();
        if (width > 0 || height > 0) {
            this.width = width;
            this.height = height;
            this.upperRight = new Vector2d(width, height);
        }
    }

    public boolean isInsideTheMap(Vector2d position) {
        return position.precedes(this.upperRight) && position.follows(new Vector2d(0, 0));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInsideTheMap(position) && !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.map.containsKey(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (animal == null) return false;
        if (animal.getPosition() == null) return false;
        if (isOccupied(animal.getPosition())) return false;
        if (!isInsideTheMap(animal.getPosition())) return false;

        if (animal.getPreviousPosition() != null) {
            this.map.remove(animal.getPreviousPosition());
        }
        this.map.put(animal.getPosition(), animal);

        return true;
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position);
    }
}
