package agh.ics.oop;

import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapTypes.IWorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class SimulationEngine implements IEngine {
    private final IWorldMap map;
    private final List<MoveDirection> moves;
    private final List<Animal> animals = new ArrayList<>();

    public SimulationEngine(List<MoveDirection> moves, IWorldMap map, List<Vector2d> initialPositions) {
        this.moves = moves;
        this.map = map;

        List<Animal> animals = initialPositions.stream()
                .map(pos -> new Animal(this.map, pos))
                .collect(Collectors.toList());


        for (Animal animal : animals) {
            if (this.map.canMoveTo(animal.getPosition())) {
                this.animals.add(animal);
                this.map.place(animal);
            }
        }
    }

    @Override
    public void run() {
        ListIterator<MoveDirection> movesIterator = this.moves.listIterator();

        while (movesIterator.hasNext()) {
            int i = movesIterator.nextIndex();
            MoveDirection moveDirection = movesIterator.next();

            this.animals.get(i % this.animals.size()).move(moveDirection);
        }

    }
}
