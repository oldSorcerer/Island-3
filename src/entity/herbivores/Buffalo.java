package entity.herbivores;

import entity.Cell;

import java.util.Objects;

public class Buffalo extends Herbivores {
    public static final int MAX_EAT_UP = 100_000;
    public static final int MAX_DEATH = 4;

    public static int newBuffalo = 0;
    public static int deathBuffalo = 0;

    private int stepToDeath =0;



    private boolean isEat = false;
    private boolean isReproduce = false;
    private boolean isMove = false;
    private String name = new String();

    public Buffalo() {
        weight = 700_000;
        stepDeath = 0;
        name = Integer.toString(newBuffalo);
        satiety = 100_000;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buffalo buffalo = (Buffalo) o;
        return Objects.equals(name, buffalo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
