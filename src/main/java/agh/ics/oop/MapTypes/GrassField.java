package agh.ics.oop.MapTypes;

import agh.ics.oop.MapBoundary;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapElements.Grass;
import agh.ics.oop.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int maxGrassX, maxGrassY;
    protected final MapBoundary mapBoundary = new MapBoundary();

    public GrassField(int noOfGrassBlocks) {
        this.maxGrassX = (int) Math.sqrt(noOfGrassBlocks*10);
        this.maxGrassY = (int) Math.sqrt(noOfGrassBlocks*10);

        for (int i = 0; i < noOfGrassBlocks; i++) {
            Vector2d grassPosition;
            do {
                int x = ThreadLocalRandom.current().nextInt(0, this.maxGrassX + 1);
                int y = ThreadLocalRandom.current().nextInt(0, this.maxGrassY + 1);
                grassPosition = new Vector2d(x, y);
            } while (this.objectAt(grassPosition) != null);
            this.map.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position != null && !(this.objectAt(position) instanceof Animal);
    }

    @Override
    public void place(Animal animal) {
        super.place(animal);

        this.map.put(animal.getPosition(), animal);
        this.mapBoundary.addWorldMapElement(animal);
    }

    @Override
    public boolean positionChanged(Object object, Vector2d oldPosition, Vector2d newPosition) {
        if (this.objectAt(newPosition) instanceof Grass) {
            this.map.remove(newPosition);
            Vector2d grassPosition;
            do {
                int x = ThreadLocalRandom.current().nextInt(0, this.maxGrassX + 1);
                int y = ThreadLocalRandom.current().nextInt(0, this.maxGrassY + 1);
                grassPosition = new Vector2d(x, y);
            } while (this.isOccupied(grassPosition));
            this.map.put(grassPosition, new Grass(grassPosition));
        }
        return super.positionChanged(object, oldPosition, newPosition);
    }

    @Override
    public Vector2d getLowerLeftDrawLimit() {
        if (this.map.isEmpty()) {
            return new Vector2d(0, 0);
        }

        return this.mapBoundary.getLowerLeftCorner();
    }

    @Override
    public Vector2d getUpperRightDrawLimit() {
        if (this.map.isEmpty()) {
            return new Vector2d(0, 0);
        }

        return this.mapBoundary.getUpperRightCorner();
    }
}
