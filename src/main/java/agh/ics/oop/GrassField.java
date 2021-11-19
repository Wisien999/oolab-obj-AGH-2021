package agh.ics.oop;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int maxGrassX, maxGrassY;

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
        return !(this.objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (animal == null) return false;
        if (animal.getPosition() == null) return false;

        if (animal.getPreviousPosition() != null) {
            this.map.remove(animal.getPreviousPosition());
        }

        if (this.objectAt(animal.getPosition()) instanceof Grass) {
            Vector2d grassPosition;
            do {
                int x = ThreadLocalRandom.current().nextInt(0, this.maxGrassX + 1);
                int y = ThreadLocalRandom.current().nextInt(0, this.maxGrassY + 1);
                grassPosition = new Vector2d(x, y);
            } while (this.isOccupied(grassPosition) || grassPosition.equals(animal.getPosition()));
            this.map.put(grassPosition, new Grass(grassPosition));
        }

        this.map.put(animal.getPosition(), animal);

        return true;
    }

    @Override
    public Vector2d getLowerLeftDrawLimit() {
        Set<Vector2d> positionsSet = this.map.keySet();
        return positionsSet.stream().reduce(positionsSet.iterator().next(), Vector2d::lowerLeft);
    }

    @Override
    public Vector2d getUpperRightDrawLimit() {
        Set<Vector2d> positionsSet = this.map.keySet();
        return positionsSet.stream().reduce(positionsSet.iterator().next(), Vector2d::upperRight);
    }
}
