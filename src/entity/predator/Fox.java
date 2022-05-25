package entity.predator;

import entity.Cell;

import java.util.ArrayList;

public class Fox extends Predator{
    public static final double MAX_EAT_UP = 1;
    public static final int MAX_DEATH = 8;
    public static int newFox = 0;
    public static int deathFox = 0;
    public Fox() {
        weight = 8_000;
        stepDeath = 0;
    }

    @Override
    public void eat() {

    }

    @Override
    public void move(Cell[][] cells) {

    }

    @Override
    public ArrayList<Fox> reproduce(Cell[][] cells, int i, int j) {
        return null;
    }
}
