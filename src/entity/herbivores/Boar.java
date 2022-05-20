package entity.herbivores;

import entity.Cell;

public class Boar extends Herbivores {
    public static final double MAX_EAT_UP = 7;
    public static final int MAX_DEATH = 8;
    public static int newBoar = 0;
    public static int deathBoar = 0;
    public Boar() {
        weight = 400_000;
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
