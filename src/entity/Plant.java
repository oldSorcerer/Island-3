package entity;

public class Plant {
    public static long newPlants = 0;
    public static long deathPlants = 0;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    //измеряю в граммах
    private int weight = 1000;
}
