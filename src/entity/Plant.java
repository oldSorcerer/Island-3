package entity;

import java.util.Iterator;
import java.util.Objects;

public class Plant {
    public static long newPlants = 0;
    public static long deathPlants = 0;
    //измеряю в граммах
    private int weight = 1000;

    private String name = new String();

    public Plant() {
        this.name = Long.toString(newPlants);
        this.weight = 1_000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(name, plant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

}
