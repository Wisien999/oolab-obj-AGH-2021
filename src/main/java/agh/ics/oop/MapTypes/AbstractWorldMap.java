package agh.ics.oop.MapTypes;

import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.MapElements.AbstractWorldMapElement;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapVisualizer;
import agh.ics.oop.Vector2d;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final Map<Vector2d, AbstractWorldMapElement> map = new LinkedHashMap<>();

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.map.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.map.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(this.getLowerLeftDrawLimit(), this.getUpperRightDrawLimit());
    }

    @Override
    public boolean place(Animal animal) {
        if (animal == null) return false;
        if (animal.getPosition() == null) return false;
        if (this.map.get(animal.getPosition()) instanceof Animal) return false;


        animal.addObserver(this);

        return true;
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement worldMapElement = this.map.get(oldPosition);
        this.map.put(newPosition, worldMapElement);
        this.map.remove(oldPosition);

        return true;
    }

    public abstract Vector2d getLowerLeftDrawLimit();
    public abstract Vector2d getUpperRightDrawLimit();
}
