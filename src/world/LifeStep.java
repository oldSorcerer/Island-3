package world;

public class LifeStep implements Runnable{
    public static int lifeStep=1;
    @Override
    public void run() {
        lifeStep++;

    }
}
