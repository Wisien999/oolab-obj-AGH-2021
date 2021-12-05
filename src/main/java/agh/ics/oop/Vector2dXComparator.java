package agh.ics.oop;

import java.util.Comparator;

public class Vector2dXComparator implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d o1, Vector2d o2) {
        return o1.x - o2.x;
    }
}
