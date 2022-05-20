package entity.predator;

import entity.Cell;

public class Boa extends Predator{
    public static final double MAX_EAT_UP = 0.3;
    public static final int MAX_DEATH = 15;
    public static int newBoa = 0;
    public static int deathBoa = 0;
    public Boa() {
        weight = 15_000;
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
