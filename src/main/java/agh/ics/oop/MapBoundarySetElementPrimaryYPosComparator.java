package agh.ics.oop;

import agh.ics.oop.MapElements.Animal;

import java.util.Comparator;

public class MapBoundarySetElementPrimaryYPosComparator implements Comparator<MapBoundarySetElement> {
    @Override
    public int compare(MapBoundarySetElement o1, MapBoundarySetElement o2) {
        Vector2dYComparator vector2dYComparator = new Vector2dYComparator();
        int yPosResult = vector2dYComparator.compare(o1.getPosition(), o2.getPosition());
        if (yPosResult != 0) {
            return yPosResult;
        }

        Vector2dXComparator vector2dXComparator = new Vector2dXComparator();
        int xPosResult = vector2dXComparator.compare(o1.getPosition(), o2.getPosition());
        if (xPosResult != 0) {
            return xPosResult;
        }

        if (o1.getWorldMapElement() instanceof Animal) {
            return 1;
        }

        return 0;
    }
}
