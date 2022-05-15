package entity.herbivores;

public class Hamster extends Herbivores {
    public static final double MAX_EAT_UP = 0.0075;
    public static final int MAX_DEATH = 3;
    public static int newHamster = 0;
    public static int deathHamster= 0;
    public Hamster() {
        weight = 0.03;
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
