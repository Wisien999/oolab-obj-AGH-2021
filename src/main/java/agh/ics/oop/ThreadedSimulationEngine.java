package agh.ics.oop;

import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.MapTypes.IWorldMap;
import agh.ics.oop.gui.IMapRefreshNeededObserver;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ThreadedSimulationEngine implements IEngine, Runnable {
    private final IWorldMap map;
    private List<MoveDirection> moves;
    private final List<Animal> animals = new ArrayList<>();
    private List<IMapRefreshNeededObserver> mapRefreshNeededObservers= new ArrayList<>();

    private final int moveDelay;

    public ThreadedSimulationEngine(IWorldMap map, List<Vector2d> initialPositions, int moveDelay) {
        this.map = map;
        this.moveDelay = moveDelay;

        List<Animal> animals = initialPositions.stream()
                .map(pos -> new Animal(this.map, pos))
                .collect(Collectors.toList());


        for (Animal animal : animals) {
            this.map.place(animal);
            this.animals.add(animal);
        }
    }

    public ThreadedSimulationEngine(List<MoveDirection> moves, IWorldMap map, List<Vector2d> initialPositions, int moveDelay) {
        this(map, initialPositions, moveDelay);
        this.setMoves(moves);
    }

    void mapRefreshNeeded() {
        for (IMapRefreshNeededObserver observer : this.mapRefreshNeededObservers) {
            observer.refresh();
        }
    }

    @Override
    public void run() {
        ListIterator<MoveDirection> movesIterator = this.moves.listIterator();
        try {
            while(movesIterator.hasNext()) {
                int i = movesIterator.nextIndex();
                MoveDirection moveDirection = movesIterator.next();

                this.animals.get(i % this.animals.size()).move(moveDirection);

                this.mapRefreshNeeded();
                //noinspection BusyWait
                Thread.sleep(moveDelay);
            }
        } catch (InterruptedException e) {
            System.err.println("Interruption while waiting for animal move!");
        }
    }


    public void setMoves(List<MoveDirection> moves) {
        this.moves = moves;
    }

    public void addObserver(IMapRefreshNeededObserver observer) {
        this.mapRefreshNeededObservers.add(observer);
    }
    public void removeObserver(IMapRefreshNeededObserver observer) {
        this.mapRefreshNeededObservers.remove(observer);
    }
}
