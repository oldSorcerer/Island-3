package entity.predator;

import entity.Cell;

public class Bear extends Predator{
    public static final double MAX_EAT_UP = 38;
    public static final int MAX_DEATH = 15;
    public static int newBear = 0;
    public static int deathBear = 0;
    public Bear() {
        weight = 500_000;
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
