package entity.predator;

import entity.Animal;
import entity.Cell;
import entity.herbivores.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal {
    public static int newWolf = 0;
    public static int deathWolf = 0;
    public Wolf() {
        weight = 50_000;
        name = Integer.toString(newWolf);
        satiety = 8_000;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wolf wolf = (Wolf) o;
        return Objects.equals(name, wolf.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Wolf> wolves = cells[i][j].getWolf();
        ArrayList<Wolf> newWolf = new ArrayList<>();
        ArrayList<Wolf> arrayListWolfNeedToDelete = new ArrayList<>();
        for (int k = 0; k < wolves.size(); k++) {
            Wolf wolf = wolves.get(k);
            //выбираем что кушаем
            //0-duck
            //1-buffalo
            //2-boar
            //3-sheep
            //4-gaot
            //5-mouse
            //6-rabbit
            //7-deer
            //8-horse
            int randomFood = ThreadLocalRandom.current().nextInt(3);
            if(randomFood == 0){
                ArrayList<Duck> ducks = cells[i][j].getDuck();
                wolf.eat(ducks);
            }else if(randomFood == 1){
                ArrayList<Buffalo> buffaloes = cells[i][j].getBuffalo();
                wolf.eat(buffaloes);
            }else if(randomFood == 2){
                ArrayList<Boar> boars = cells[i][j].getBoar();
                wolf.eat(boars);
            }else if(randomFood == 3){
                ArrayList<Sheep> sheeps = cells[i][j].getSheep();
                wolf.eat(sheeps);
            }else if(randomFood == 4){
                ArrayList<Goat> goats = cells[i][j].getGoat();
                wolf.eat(goats);
            }else if(randomFood == 5){
                ArrayList<Mouse> mouses = cells[i][j].getMouse();
                wolf.eat(mouses);
            }else if(randomFood == 6){
                ArrayList<Rabbit> rabbits = cells[i][j].getRabbit();
                wolf.eat(rabbits);
            }else if(randomFood == 7){
                ArrayList<Deer> deers = cells[i][j].getDeer();
                wolf.eat(deers);
            }else if(randomFood == 8){
                ArrayList<Horse> horses = cells[i][j].getHorse();
                wolf.eat(horses);
            }
            //размножаемся
            newWolf.addAll(wolf.reproduce(cells,i,j));
//
            //передвижение
            Wolf wolfMove = wolf.move(cells,i,j);
            if(wolfMove != null){
                arrayListWolfNeedToDelete.add(wolfMove);
            }
//
        }
        wolves.addAll(newWolf);
        for (Wolf wolf: arrayListWolfNeedToDelete) {
            wolves.remove(wolf);
        }
//
        int needToKill =0;
        Iterator<Wolf> iterator = wolves.iterator();
        if(wolves.size()>30){
            needToKill = Math.abs(wolves.size()-30);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Wolf.deathWolf++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Wolf wolf = iterator.next();
                if(wolf.stepDeath==3){
                    iterator.remove();
                    Wolf.deathWolf++;
                }
            }
        }
    }

    public <T> void  eat(ArrayList<T> animal){
        Object object = null;
        if(animal.size()>0){
            object = animal.get(0);
            if(object instanceof Duck){
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<40)) {
                    Iterator<T> iterator = animal.iterator();
                    int needToEat = 8;
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Duck.deathDuck++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            }else if(object instanceof Buffalo){
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<10)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Buffalo.deathBuffalo++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            } else if (object instanceof Boar) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<15)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Boar.deathBoar++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            } else if (object instanceof Sheep) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<70)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Sheep.deathSheep++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            } else if (object instanceof Goat) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<60)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Goat.deathGoat++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Mouse) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 16;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<80)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Mouse.deathMouse++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Rabbit) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 16;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<60)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Rabbit.deathRabbit++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Deer) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<15)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Deer.deathDeer++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Horse) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<10)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Horse.deathHorse++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 8_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 2_700;
                    if(this.satiety<0)stepDeath++;
                }
            }
        }else{
            this.isEat = false;
            this.satiety -= 2_700;
            if(this.satiety<0)stepDeath++;
        }
    }

    @Override
    public ArrayList<Wolf> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Wolf> newWolf= new ArrayList<>();
        int randomLengthWolf = ThreadLocalRandom.current().nextInt(2);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthWolf;k++){
                newWolf.add(new Wolf());
                Wolf.newWolf++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety -= 2_700;;
        if(this.satiety<0)stepDeath++;
        return newWolf;
    }

    @Override
    public Wolf move(Cell[][] cells,int i,int j) {
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(4);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>=0){
                    this.satiety-=2_700;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Wolf> MoveToOtherWolf = cells[i][iStepToLeft].getWolf();
                    MoveToOtherWolf.add(this);
                    return this;
                }else{
                    Wolf.deathWolf++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>=0){
                    this.satiety-=2_700;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Wolf> MoveToOtherWolf = cells[iStepToUp][j].getWolf();
                    MoveToOtherWolf.add(this);
                    return this;
                }else{
                    Wolf.deathWolf++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety-=2_700;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Wolf> MoveToOtherWolf = cells[i][iStepToRight].getWolf();
                    MoveToOtherWolf.add(this);
                    return this;
                }else{
                    Wolf.deathWolf++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety-=2_700;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Wolf> MoveToOtherWolf = cells[iStepToDown][j].getWolf();
                    MoveToOtherWolf.add(this);
                    return this;
                }else{
                    Wolf.deathWolf++;
                    return this;
                }
            }
        }else{
            this.isMove = false;
        }
        return  null;
    }

    @Override
    public void eat() {

    }

}
