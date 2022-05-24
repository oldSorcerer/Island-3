package entity.herbivores;

import entity.Animal;
import entity.Cell;
import entity.Plant;
import entity.predator.Boa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivores {
    public static final double MAX_EAT_UP = 7;
    public static final int MAX_DEATH = 8;

    public static int newBoar = 0;
    public static int deathBoar = 0;
    private int stepToDeath =0;
    private boolean isEat = false;
    private boolean isReproduce = false;
    private boolean isMove = false;
    private String name = new String();
    public Boar() {
        weight = 400_000;
        stepDeath = 0;
        name = Integer.toString(newBoar);
        satiety = 50_000;
    }

    public int getStepToDeath() {
        return stepToDeath;
    }

    public void setStepToDeath(int stepToDeath) {
        this.stepToDeath = stepToDeath;
    }

    public boolean isEat() {
        return isEat;
    }

    public void setEat(boolean eat) {
        isEat = eat;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boar boar = (Boar) o;
        return Objects.equals(name, boar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Boar{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Boar> boars = cells[i][j].getBoar();
        ArrayList<Boar> newBoar = new ArrayList<>();
        ArrayList<Boar> arrayListDusckNeedToDelete = new ArrayList<>();
        for (int k = 0; k < boars.size(); k++) {
            Boar boar = boars.get(k);

            //выбираем что кушаем
            //0-caterpillar
            //1-plant
            //2-mouse
            int randomFood = ThreadLocalRandom.current().nextInt(2);
            if(randomFood == 0){
                ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
                boar.eat(caterpillars);
            }else if(randomFood == 1){
                List<Plant> plants = cells[i][j].getSynchronizedPlant();
                boar.eat(plants);
            }else if(randomFood == 2){
                ArrayList<Mouse> Mouses = cells[i][j].getMouse();
                boar.eat(Mouses);
            }
//---------------------------------------
            //размножаемся
//            newDuck.addAll(duck.reproduce(cells,i,j,false));
//
//            //передвижение
//            Duck duckMove = duck.move(cells,i,j);
//            if(duckMove != null){
//                arrayListDusckNeedToDelete.add(duckMove);
//            }
//
        }
//        ducks.addAll(newDuck);
//        for (Duck duck: arrayListDusckNeedToDelete) {
//            ducks.remove(duck);
//        }
//
        int needToKill =0;
        Iterator<Boar> iterator = boars.iterator();

        if(boars.size()>50){
            needToKill = Math.abs(boars.size()-50);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Boar.deathBoar++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Boar boar = iterator.next();
                if(boar.stepDeath==2){
                    iterator.remove();
                    Boar.deathBoar++;
                }
            }
        }
    }
    public void eat(List<Plant> plants){
        //кабаны едят половина растений на карте, если он даже немного поел ставлю ему поленое насыщение иноже он вымирает
        if(this.isEat == false && this.isReproduce == false  && this.isMove == false) {
            Iterator<Plant> iterator = plants.iterator();
            int needToEat = 50;
            while (iterator.hasNext()){
                iterator.next();
                iterator.remove();
                Plant.deathPlants++;
                needToEat--;
                if(needToEat ==0)break;
            }
            this.satiety=50_000;


            this.stepDeath =0;
            this.isEat = true;
        }else{
            this.isEat = false;
            this.satiety-=12_500;
            if(this.satiety<0)stepDeath++;
        }
    }

    public <T> void  eat(ArrayList<T> animal){
        Object object = null;
        //System.out.println("eat Animal");
        if(animal.size()>0){
            object = animal.get(0);
            if(object instanceof Caterpillar){
                System.out.println("eat caterpillar");
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<90)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Caterpillar.deathGaterpillar++;
                    }
                    this.satiety = 50_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety-=12_500;
                    if(this.satiety<0)stepDeath++;
                }
            }else if(object instanceof Mouse){
                System.out.println("eat mouse");
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<50)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Mouse.deathMouse++;
                    }
                    this.satiety = 50_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety-=12_500;
                    if(this.satiety<0)stepDeath++;
                }
            }
        }else{
            this.isEat = false;
            this.satiety-=12_500;
            if(this.satiety<0)stepDeath++;
        }


        //ест все растения, ставим ему полное насыщение

    }


    @Override
    public void eat() {

    }

    @Override
    public void move(Cell[][] cells) {

    }

    @Override
    public void reproduce(Cell[][] cells, int i, int j) {

    }
}
