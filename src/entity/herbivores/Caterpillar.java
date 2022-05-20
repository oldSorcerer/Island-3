package entity.herbivores;

import entity.Cell;
import entity.Plant;
import world.LifeCycle;
import world.LifeStep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivores {
    // гусеница может скущать за раз 0.0025 кг = (округлил) 3 грамм
    public static final int MAX_EAT_UP = 3;

    public static int newGaterpillar = 0;
    public static int deathGaterpillar = 0;
    private boolean isEat = false;
    private boolean isMove = false;
    private boolean isReproduce = false;

    //0 - woman
    //1 - man
    private int intGender ;

    public boolean isReproduce() {
        return isReproduce;
    }

    public void setReproduce(boolean reproduce) {
        isReproduce = reproduce;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public boolean isEat() {
        return isEat;
    }

    public void setEat(boolean eat) {
        isEat = eat;
    }

    public Caterpillar() {
        weight = 10;
        satiety = 3;
        isEat = true;
        isMove = false;
        intGender = ThreadLocalRandom.current().nextInt(2);
    }
    public int getIntGender() {
        return intGender;
    }
    //гусеницы питаются в методее жизнь, потому что едят(400 гусениц съедают 1 растение)
    @Override
    public void eat() {

    }

    @Override
    public void move(Cell[][] cells ) {

    }
    public void eat(Plant plant){
        if(plant.getWeight()>3 && this.isEat == false && this.isReproduce == false){
            plant.setWeight(plant.getWeight()-3);
            this.satiety=3;
            this.isEat = true;
        }else {
            this.isEat = false;
        }
    }
    public void reproduce(Cell[][] cells, int i, int j){

    }
    public ArrayList<Caterpillar> reproduce(Cell[][] cells, int i, int j,boolean f) {
        ArrayList<Caterpillar> newCaterpillar = new ArrayList<>();
        if(this.isReproduce == false && this.intGender == 0 && this.isEat == false){
            ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
            for (int k=0;k<5;k++){
                if(Cell.MAX_CATERPILLAR>caterpillars.size()+newCaterpillar.size()){
                    newCaterpillar.add(new Caterpillar());
                    Caterpillar.newGaterpillar++;
                }
            }
            this.isReproduce=true;

        }else{
            this.isReproduce = false;
        }
        this.satiety=0;
        return newCaterpillar;
    }

    @Override
    public String toString() {
        return "Caterpillar{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }
}
