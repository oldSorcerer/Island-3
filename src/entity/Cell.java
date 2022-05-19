package entity;

import entity.herbivores.*;
import entity.predator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell {
    public static final int MAX_WOLF = 30;
    public static final int MAX_BOA = 30;
    public static final int MAX_FOX = 30;
    public static final int MAX_BEAR = 5;
    public static final int MAX_EAGLE = 20;
    public static final int MAX_HORSE = 20;
    public static final int MAX_DEER = 20;
    public static final int MAX_RABBIT = 150;
    public static final int MAX_MOUSE = 500;
    public static final int MAX_GOAT = 140;
    public static final int MAX_SHEEP = 140;
    public static final int MAX_BOAR = 50;
    public static final int MAX_BUFFALO = 10;
    public static final int MAX_DUCK = 200;
    public static final int MAX_CATERPILLAR = 1000;
    public static final int MAX_PLANT = 200;



    private ArrayList<Wolf> wolf = new ArrayList<>();
    private ArrayList<Boa> boa = new ArrayList<>();
    private ArrayList<Fox> fox = new ArrayList<>();
    private ArrayList<Bear> bear = new ArrayList<>();
    private ArrayList<Eagle> eagle =new ArrayList<>();
    private ArrayList<Horse> horse = new ArrayList<>();
    private ArrayList<Deer> deer = new ArrayList<>();
    private ArrayList<Rabbit> rabbit = new ArrayList<>();
    private ArrayList<Mouse> mouse = new ArrayList<>();
    private ArrayList<Goat> goat = new ArrayList<>();
    private ArrayList<Sheep> sheep = new ArrayList<>();
    private ArrayList<Boar> boar = new ArrayList<>();
    private ArrayList<Buffalo> buffalo = new ArrayList<>();
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

    public ArrayList<Boa> getBoa() {
        return boa;
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

    public ArrayList<Mouse> getMouse() {
        return mouse;
    }

    public ArrayList<Goat> getGoat() {
        return goat;
    }

    public ArrayList<Sheep> getSheep() {
        return sheep;
    }

    public ArrayList<Boar> getBoar() {
        return boar;
    }

    public ArrayList<Buffalo> getBuffalo() {
        return buffalo;
    }

    public ArrayList<Duck> getDuck() {
        return duck;
    }

    @Override
    public String toString() {
        return  "wolf=" + wolf.size() +
                ", snake=" + boa.size() +
                ", fox=" + fox.size() +
                ", bear=" + bear.size() +
                ", eagle=" + eagle.size() +
                ", horse=" + horse.size() +
                ", deer=" + deer.size() +
                ", rabbit=" + rabbit.size() +
                ", hamster=" + mouse.size() +
                ", goat=" + goat.size() +
                ", sheep=" + sheep.size() +
                ", boar=" + boar.size() +
                ", cow=" + buffalo.size() +
                ", duck=" + duck.size() +
                ", caterpillar=" + caterpillar.size() +
                ", plant=" + synchronizedPlant.size();
    }
}
