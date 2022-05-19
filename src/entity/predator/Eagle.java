package entity.predator;

public class Eagle extends Predator{
    public static final double MAX_EAT_UP = 1;
    public static final int MAX_DEATH = 5;
    public static int newEagle = 0;
    public static int deathEagle = 0;
    public Eagle() {
        weight = 6_000;
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
