package entity;

import entity.herbivores.*;
import entity.predator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell {
    static final int MAX_WOLF = 30;
    static final int MAX_SNAKE = 123;
    static final int MAX_FOX = 50;
    static final int MAX_BEAR = 7;
    static final int MAX_EAGLE = 166;
    static final int MAX_HORSE = 23;
    static final int MAX_DEER = 41;
    static final int MAX_RABBIT = 750;
    static final int MAX_HAMSTER = 10_000;
    static final int MAX_GOAT = 107;
    static final int MAX_SHEEP = 156;
    static final int MAX_KANGAROO = 20;
    static final int MAX_COW = 20;
    public static final int MAX_DUCK = 500;
    public static final int MAX_CATERPILLAR = 10_000;
    public static final int MAX_PLANT = 10_000;

    private ArrayList<Wolf> wolf = new ArrayList<>();
    private ArrayList<Snake> snake = new ArrayList<>();
    private ArrayList<Fox> fox = new ArrayList<>();
    private ArrayList<Bear> bear = new ArrayList<>();
    private ArrayList<Eagle> eagle =new ArrayList<>();
    private ArrayList<Horse> horse = new ArrayList<>();
    private ArrayList<Deer> deer = new ArrayList<>();
    private ArrayList<Rabbit> rabbit = new ArrayList<>();
    private ArrayList<Hamster> hamster = new ArrayList<>();
    private ArrayList<Goat> goat = new ArrayList<>();
    private ArrayList<Sheep> sheep = new ArrayList<>();
    private ArrayList<Kangaroo> kangaroo = new ArrayList<>();
    private ArrayList<Cow> cow = new ArrayList<>();
    private ArrayList<Duck> duck = new ArrayList<>();
    private ArrayList<Caterpillar> caterpillar = new ArrayList<>();

    private ArrayList<Plant> plant = new ArrayList<>();
    private List<Plant> synchronizedPlant = Collections.synchronizedList(plant);

    public List<Plant> getSynchronizedPlant() {
        return synchronizedPlant;
    }

    public ArrayList<Caterpillar> getCaterpillar() {
        return caterpillar;
    }
    public ArrayList<Wolf> getWolf() {
        return wolf;
    }

    public ArrayList<Snake> getSnake() {
        return snake;
    }

    public ArrayList<Fox> getFox() {
        return fox;
    }

    public ArrayList<Bear> getBear() {
        return bear;
    }

    public ArrayList<Eagle> getEagle() {
        return eagle;
    }

    public ArrayList<Horse> getHorse() {
        return horse;
    }

    public ArrayList<Deer> getDeer() {
        return deer;
    }

    public ArrayList<Rabbit> getRabbit() {
        return rabbit;
    }

    public ArrayList<Hamster> getHamster() {
        return hamster;
    }

    public ArrayList<Goat> getGoat() {
        return goat;
    }

    public ArrayList<Sheep> getSheep() {
        return sheep;
    }

    public ArrayList<Kangaroo> getKangaroo() {
        return kangaroo;
    }

    public ArrayList<Cow> getCow() {
        return cow;
    }

    public ArrayList<Duck> getDuck() {
        return duck;
    }

    @Override
    public String toString() {
        return  "wolf=" + wolf.size() +
                ", snake=" + snake.size() +
                ", fox=" + fox.size() +
                ", bear=" + bear.size() +
                ", eagle=" + eagle.size() +
                ", horse=" + horse.size() +
                ", deer=" + deer.size() +
                ", rabbit=" + rabbit.size() +
                ", hamster=" + hamster.size() +
                ", goat=" + goat.size() +
                ", sheep=" + sheep.size() +
                ", kangaroo=" + kangaroo.size() +
                ", cow=" + cow.size() +
                ", duck=" + duck.size() +
                ", caterpillar=" + caterpillar.size() +
                ", plant=" + synchronizedPlant.size();
    }
}
