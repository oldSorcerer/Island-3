package entity.herbivores;

import entity.Cell;

import java.util.Objects;

public class Sheep extends Herbivores {
    public static final int MAX_EAT_UP = 15_000;
    public static final int MAX_DEATH = 3;

    public static int newSheep = 0;
    public static int deathSheep = 0;
    private int stepToDeath =0;
    private boolean isEat = false;
    private boolean isReproduce = false;
    private boolean isMove = false;

    private String name = new String();
    public Sheep() {
        weight = 70_000;
        stepDeath = 0;
        name = Integer.toString(newSheep);
        satiety = 15_000;
    }


    public int getStepToDeath() {
        return stepToDeath;
    }

    public void setStepToDeath(int stepToDeath) {
        this.stepToDeath = stepToDeath;
    }

    public boolean isEat() {
        return isEat;
    }

    public void setEat(boolean eat) {
        isEat = eat;
    }

    public boolean isReproduce() {
        return isReproduce;
    }

    public void setReproduce(boolean reproduce) {
        isReproduce = reproduce;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sheep sheep = (Sheep) o;
        return Objects.equals(name, sheep.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
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
