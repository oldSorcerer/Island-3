package entity.herbivores;

public class Mouse extends Herbivores {
    public static final double MAX_EAT_UP = 0.0075;
    public static final int MAX_DEATH = 3;
    public static int newMouse = 0;
    public static int deathMouse= 0;
    public Mouse() {
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