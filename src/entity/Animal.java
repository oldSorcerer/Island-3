package entity;

import entity.verb.Eat;
import entity.verb.Move;
import entity.verb.Reproduce;

public abstract class Animal implements Eat, Move, Reproduce {
    //перевел кг в граммы 1 кг = 1000 грамм
    protected int weight;
    public int satiety;
    public int stepDeath;

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
