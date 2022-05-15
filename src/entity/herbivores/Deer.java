package entity.herbivores;

public class Deer extends Herbivores {
    public static final double MAX_EAT_UP = 26;
    public static final int MAX_DEATH = 4;
    public static int newDeer= 0;
    public static int deathDeer = 0;
    public Deer() {
        weight = 170;
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
