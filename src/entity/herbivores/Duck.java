package entity.herbivores;

import entity.Cell;
import entity.Plant;
import world.LifeStep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivores {
    public static final int MAX_EAT_UP = 150;
    public static final int MAX_DEATH = 4;

    private int stepToDeath =0;
    public static int newDuck= 0;
    public static int deathDuck = 0;

    private boolean isEat = false;
    private boolean isReproduce = false;
    private boolean isMove = false;
    private String name = new String();

    public Duck() {
        weight = 1000;
        stepDeath = 0;
        name = Integer.toString(newDuck);
        satiety = 150;
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
        Duck duck = (Duck) o;
        return Objects.equals(name, duck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + name + '\'' +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }
    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Duck> ducks = cells[i][j].getDuck();
        ArrayList<Duck> newDuck = new ArrayList<>();
        ArrayList<Duck> arrayListDusckNeedToDelete = new ArrayList<>();
        for (int k = 0; k < ducks.size(); k++) {
            Duck duck = ducks.get(k);
            //выбираем что кушаем
            //0-caterpillar
            //1-plant
            int randomFood = ThreadLocalRandom.current().nextInt(2);
            if(randomFood == 0){
                ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
                duck.eat(caterpillars);
            }else{
                Plant plant = cells[i][j].getSynchronizedPlant().get(ThreadLocalRandom.current().nextInt(cells[i][j].getSynchronizedPlant().size()));
                duck.eat(plant);
            }
            newDuck.addAll(duck.reproduce(cells,i,j,false));
            //move
            Duck duckMove = duck.move(cells,i,j);
            if(duckMove != null) arrayListDusckNeedToDelete.add(duckMove);

        }
        ducks.addAll(newDuck);
        for (Duck duck: arrayListDusckNeedToDelete) {
            ducks.remove(duck);
        }
        int needToKill =0;
        Iterator<Duck> iterator = ducks.iterator();

        if(ducks.size()>200){
            needToKill = Math.abs(ducks.size()-200);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Duck.deathDuck++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Duck duck = iterator.next();
                if(duck.stepDeath==4){
                    iterator.remove();
                    Duck.deathDuck++;
                }
            }
        }
    }

    public void eat(Plant plant){
        if(plant.getWeight()>150 && this.isEat == false && this.isReproduce == false && this.isMove == false){
            plant.setWeight(plant.getWeight()-150);
            this.satiety=150;
            this.stepDeath =0;
            this.isEat = true;
        }else {
            this.isEat = false;
            this.satiety-=38;
            if(this.satiety<0)stepDeath++;
        }
    }
    public void eat(ArrayList<Caterpillar> caterpillars){
        int chanceToEat = ThreadLocalRandom.current().nextInt(100);
        chanceToEat++;

        if(caterpillars.size()>15 && this.isEat == false && this.isReproduce == false && (chanceToEat>90) && this.isMove == false) {
            Iterator<Caterpillar> iterator = caterpillars.iterator();
            int needToEat = 15;
            while (iterator.hasNext()){
                iterator.next();
                iterator.remove();
                Caterpillar.deathGaterpillar++;
                needToEat--;
                if(needToEat ==0)break;
            }
            this.satiety=150;
            this.stepDeath =0;
            this.isEat = true;
        }else{
            this.isEat = false;
            this.satiety-=38;
            if(this.satiety<0)stepDeath++;
        }
    }

    public ArrayList<Duck> reproduce(Cell[][] cells, int i, int j,boolean f) {
        ArrayList<Duck> newDuck= new ArrayList<>();
        int randomLengthCaterpillar = ThreadLocalRandom.current().nextInt(2);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthCaterpillar;k++){
                newDuck.add(new Duck());
                Duck.newDuck++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety-=38;
        if(this.satiety<0)stepDeath++;
        return newDuck;
    }

    public Duck move(Cell[][] cells,int i,int j) {

        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(5);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>0){
                    this.satiety-=38;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Duck> MoveToOtherDucks = cells[i][randomStepLength].getDuck();
                    MoveToOtherDucks.add(this);
                    return this;
                }else{
                    Duck.deathDuck++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>0){
                    this.satiety-=38;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Duck> MoveToOtherDucks = cells[iStepToUp][j].getDuck();
                    MoveToOtherDucks.add(this);
                    return this;
                }else{
                    Duck.deathDuck++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety-=38;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Duck> MoveToOtherDucks = cells[i][iStepToRight].getDuck();
                    MoveToOtherDucks.add(this);
                    return this;
                }else{
                    Duck.deathDuck++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety-=38;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Duck> MoveToOtherDucks = cells[iStepToDown][j].getDuck();
                    MoveToOtherDucks.add(this);
                    return this;
                }else{
                    Duck.deathDuck++;
                    return this;
                }
            }
        }else{
            this.isMove = false;
        }
        return  null;
    }
    @Override
    public void move(Cell[][] cells) {

    }
//
//        //передвижение произходит по строке, все движутся к концустроки
//        }else {
//            int step = ThreadLocalRandom.current().nextInt(2);
//            step++;
//            Iterator<Duck> iterator = duck.iterator();
//            Duck duck1 = null;
//            while (iterator.hasNext()){
//                duck1 = iterator.next();
//                System.out.println(duck1);
//                if(duck1.satiety<0.12){
//                    System.out.println("move " + duck1);
//                    iterator.remove();
//                    if(j+step>cells[0].length-1){
//                        cells[i][cells[0].length-1].getDuck().add(duck1);
//                    }else{
//                        cells[i][j+step].getDuck().add(duck1);
//                    }
//                }
//            }
//
//        }
//

    @Override
    public void eat() {

    }



    @Override
    public void reproduce(Cell[][] cells, int i, int j) {

    }
}

