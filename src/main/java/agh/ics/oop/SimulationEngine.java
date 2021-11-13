package agh.ics.oop;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class SimulationEngine implements IEngine {
    private IWorldMap map;
    private List<MoveDirection> moves;
    private List<Animal> animals;

    public SimulationEngine(List<MoveDirection> moves, IWorldMap map, List<Vector2d> initialPositions) {
        this.moves = moves;
        this.map = map;

        this.animals = initialPositions.stream()
                .map(pos -> new Animal(this.map, pos))
                .collect(Collectors.toList());


        for (Animal animal :
                this.animals) {
            if (this.map.canMoveTo(animal.getPosition())) this.map.place(animal);
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
