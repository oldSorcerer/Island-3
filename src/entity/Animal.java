package entity;

public abstract class Animal {
    protected double weight;
    protected double satiety;
    protected int stepDeath;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStepDeath() {
        return stepDeath;
    }

    public void setStepDeath(int stepDeath) {
        this.stepDeath = stepDeath;
    }
    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }
}
