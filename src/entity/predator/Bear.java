package entity.predator;

import entity.Cell;
import entity.herbivores.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator{
    public static int newBear = 0;
    public static int deathBear = 0;
    public Bear() {
        weight = 500_000;
        stepDeath = 0;
        name = Integer.toString(newBear);
        satiety = 25_000;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bear bear = (Bear) o;
        return Objects.equals(name, bear.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Bear{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Bear> bears = cells[i][j].getBear();
        ArrayList<Bear> newBear = new ArrayList<>();
        ArrayList<Bear> arrayListBearNeedToDelete = new ArrayList<>();
        for (int k = 0; k < bears.size(); k++) {
            Bear bear = bears.get(k);

            //выбираем что кушаем
            //0-duck
            //1-buffalo
            //2-boar
            //3-sheep
            //4-goat
            //5-mouse
            //6-rabbit
            //7-deer
            //8-horse
            //9-boa
            int randomFood = ThreadLocalRandom.current().nextInt(3);
            if(randomFood == 0){
                ArrayList<Duck> ducks = cells[i][j].getDuck();
                bear.eat(ducks);
            }else if(randomFood == 1){
                ArrayList<Buffalo> buffaloes = cells[i][j].getBuffalo();
                bear.eat(buffaloes);
            }else if(randomFood == 2){
                ArrayList<Boar> boars = cells[i][j].getBoar();
                bear.eat(boars);
            }else if(randomFood == 3){
                ArrayList<Sheep> sheeps = cells[i][j].getSheep();
                bear.eat(sheeps);
            }else if(randomFood == 4){
                ArrayList<Goat> goats = cells[i][j].getGoat();
                bear.eat(goats);
            }else if(randomFood == 5){
                ArrayList<Mouse> mouses = cells[i][j].getMouse();
                bear.eat(mouses);
            }else if(randomFood == 6){
                ArrayList<Rabbit> rabbits = cells[i][j].getRabbit();
                bear.eat(rabbits);
            }else if(randomFood == 7){
                ArrayList<Deer> deers = cells[i][j].getDeer();
                bear.eat(deers);
            }else if(randomFood == 8){
                ArrayList<Horse> horses = cells[i][j].getHorse();
                bear.eat(horses);
            }else if(randomFood == 9){
                ArrayList<Boa> boaes = cells[i][j].getBoa();
                bear.eat(boaes);
            }
            //размножаемся
            newBear.addAll(bear.reproduce(cells,i,j));
//
            //передвижение
            Bear bearMove = bear.move(cells,i,j);
            if(bearMove != null){
                arrayListBearNeedToDelete.add(bearMove);
            }
//
        }
        bears.addAll(newBear);
        for (Bear bear: arrayListBearNeedToDelete) {
            bears.remove(bear);
        }
//
        int needToKill =0;
        Iterator<Bear> iterator = bears.iterator();
        if(bears.size()>5){
            needToKill = Math.abs(bears.size()-5);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Bear.deathBear++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Bear bear = iterator.next();
                if(bear.stepDeath==2){
                    iterator.remove();
                    Bear.deathBear++;
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
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<10)) {
                    Iterator<T> iterator = animal.iterator();
                    int needToEat = 25;
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Duck.deathDuck++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }else if(object instanceof Buffalo){
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<20)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Buffalo.deathBuffalo++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            } else if (object instanceof Boar) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<50)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Boar.deathBoar++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
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
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Goat) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<70)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Goat.deathGoat++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Mouse) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 50;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<90)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Mouse.deathMouse++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Rabbit) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 14;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<80)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Rabbit.deathRabbit++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Deer) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<80)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Deer.deathDeer++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Horse) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<40)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Horse.deathHorse++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }else if (object instanceof Boa) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 2;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<80)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Boa.deathBoa++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 25_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety -= 12_600;
                    if(this.satiety<0)stepDeath++;
                }
            }
        }else{
            this.isEat = false;
            this.satiety -= 12_600;
            if(this.satiety<0)stepDeath++;
        }
    }

    @Override
    public ArrayList<Bear> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Bear> newBear= new ArrayList<>();
        int randomLengthBear = ThreadLocalRandom.current().nextInt(2);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthBear;k++){
                newBear.add(new Bear());
                Bear.newBear++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety-=12_600;;
        if(this.satiety<0)stepDeath++;
        return newBear;
    }

    @Override
    public Bear move(Cell[][] cells,int i,int j) {
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(3);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>=0){
                    this.satiety-=12_600;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Bear> MoveToOtherBear = cells[i][iStepToLeft].getBear();
                    MoveToOtherBear.add(this);
                    return this;
                }else{
                    Bear.deathBear++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>=0){
                    this.satiety-=12_600;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Bear> MoveToOtherBear = cells[iStepToUp][j].getBear();
                    MoveToOtherBear.add(this);
                    return this;
                }else{
                    Bear.deathBear++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety-=12_600;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Bear> MoveToOtherBear = cells[i][iStepToRight].getBear();
                    MoveToOtherBear.add(this);
                    return this;
                }else{
                    Bear.deathBear++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety-=12_600;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Bear> MoveToOtherBear = cells[iStepToDown][j].getBear();
                    MoveToOtherBear.add(this);
                    return this;
                }else{
                    Bear.deathBear++;
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
