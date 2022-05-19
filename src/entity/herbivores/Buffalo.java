package entity.herbivores;

public class Buffalo extends Herbivores {
    public static final double MAX_EAT_UP = 53;
    public static final int MAX_DEATH = 4;
    public static int newCow = 0;
    public static int deathCow = 0;
    public Buffalo() {
        weight = 700_000;
        stepDeath = 0;
    }

    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}
