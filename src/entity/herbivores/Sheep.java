package entity.herbivores;

import entity.Cell;

public class Sheep extends Herbivores {
    public static final double MAX_EAT_UP = 7;
    public static final int MAX_DEATH = 5;
    public static int newSheep = 0;
    public static int deathSheep = 0;
    public Sheep() {
        weight = 70_000;
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
