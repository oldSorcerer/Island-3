package entity.herbivores;

public class Rabbit extends Herbivores {
    public static final double MAX_EAT_UP = 0.45;
    public static final int MAX_DEATH = 7;
    public static int newRabbit = 0;
    public static int deathRabbit = 0;
    public Rabbit() {
        weight = 3;
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
