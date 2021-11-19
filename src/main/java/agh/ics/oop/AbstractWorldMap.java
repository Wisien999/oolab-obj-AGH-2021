package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final Map<Vector2d, AbstractMapElement> map = new HashMap<>();

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

    public abstract Vector2d getLowerLeftDrawLimit();
    public abstract Vector2d getUpperRightDrawLimit();
}
