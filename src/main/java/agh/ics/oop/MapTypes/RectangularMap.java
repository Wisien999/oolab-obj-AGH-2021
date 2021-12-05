package agh.ics.oop.MapTypes;


import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.Vector2d;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private int width;
    private int height;
    private Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0, 0);

    public RectangularMap() {
        this.width = 5;
        this.height = 5;
        this.upperRight = new Vector2d(this.width - 1, this.height - 1);
    }

    public RectangularMap(int width, int height) {
        this();
        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
            this.upperRight = new Vector2d(width - 1, height - 1);
        }
    }

    public boolean isInsideTheMap(Vector2d position) {
        return position != null && position.precedes(this.upperRight) && position.follows(this.lowerLeft);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInsideTheMap(position) && !isOccupied(position);
    }

    @Override
    public void place(Animal animal) {
        super.place(animal);

        if (!isInsideTheMap(animal.getPosition())) {
            animal.removeObserver(this);
            throw new IllegalArgumentException("Object can't be placed on position " + animal.getPosition() + ". It is outside the world borders.");
        }

        this.map.put(animal.getPosition(), animal);
    }

    @Override
    public Vector2d getLowerLeftDrawLimit() {
        return this.lowerLeft;
    }

    @Override
    public Vector2d getUpperRightDrawLimit() {
        return this.upperRight;
    }

}
