package entity.predator;

public class Bear extends Predator{
    public static final double MAX_EAT_UP = 38;
    public static final int MAX_DEATH = 15;
    public static int newBear = 0;
    public static int deathBear = 0;
    public Bear() {
        weight = 250;
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
