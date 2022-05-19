package entity.herbivores;

public class Goat extends Herbivores {
    public static final double MAX_EAT_UP = 10;
    public static final int MAX_DEATH = 5;
    public static int newGoat = 0;
    public static int deathGoat= 0;
    public Goat() {
        weight = 60_000;
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
