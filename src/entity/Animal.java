package entity;

import entity.verb.Eat;
import entity.verb.Move;
import entity.verb.Reproduce;

public abstract class Animal implements Eat, Move, Reproduce {
    //перевел кг в граммы 1 кг = 1000 грамм
    protected int weight;
    public int satiety;
    public int stepDeath;


    protected int stepToDeath =0;
    protected boolean isEat = false;
    protected boolean isReproduce = false;
    protected boolean isMove = false;
    protected String name = new String();
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStepDeath() {
        return stepDeath;
    }

    public void setStepDeath(int stepDeath) {
        this.stepDeath = stepDeath;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }
}
