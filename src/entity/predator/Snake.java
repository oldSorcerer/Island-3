package entity.predator;

public class Snake extends Predator{
    public static final double MAX_EAT_UP = 0.3;
    public static final int MAX_DEATH = 15;
    public static int newSnake = 0;
    public static int deathSnake = 0;
    public Snake() {
        weight = 2;
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
