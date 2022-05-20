package entity.predator;

import entity.Animal;
import entity.Cell;

public class Wolf extends Animal {
    public static final double MAX_EAT_UP = 8;
    public static final int MAX_DEATH = 10;
    public static int newWolf = 0;
    public static int deathWolf = 0;
    public Wolf() {
        weight = 50_000;
        stepDeath = 0;
    }

    @Override
    public void eat() {

    }

    @Override
    public void move(Cell[][] cells) {

    }

    @Override
    public void reproduce(Cell[][] cells, int i, int j) {

    }
}
