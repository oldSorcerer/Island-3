package entity.herbivores;

import entity.Cell;
import entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Herbivores {
    public static int newRabbit = 0;
    public static int deathRabbit = 0;
    public Rabbit() {
        weight = 2_000;
        stepDeath = 0;
        name = Integer.toString(newRabbit);
        satiety = 450;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rabbit rabbit = (Rabbit) o;
        return Objects.equals(name, rabbit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Rabbit> rabbits = cells[i][j].getRabbit();
        ArrayList<Rabbit> newRabbit = new ArrayList<>();
        ArrayList<Rabbit> arrayListRabbitNeedToDelete = new ArrayList<>();
        for (int k = 0; k < rabbits.size(); k++) {
            Rabbit rabbit = rabbits.get(k);
            //кушаем
            Plant plant = null;
            if(cells[i][j].getSynchronizedPlant().size()>0){
                plant = cells[i][j].getSynchronizedPlant().get(0);
                rabbit.eat(plant);
            }

            //размножаемся
            newRabbit.addAll(rabbit.reproduce(cells,i,j));

            //передвижение
            Rabbit rabbitMove = rabbit.move(cells,i,j);
            if(rabbitMove != null){
                arrayListRabbitNeedToDelete.add(rabbitMove);
            }

        }
        rabbits.addAll(newRabbit);
        for (Rabbit rabbit: arrayListRabbitNeedToDelete) {
            rabbits.remove(rabbit);
        }

        int needToKill = 0;
        Iterator<Rabbit> iterator = rabbits.iterator();

        if(rabbits.size()>150){
            needToKill = Math.abs(rabbits.size()-150);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Rabbit.deathRabbit++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Rabbit rabbit = iterator.next();
                if(rabbit.stepDeath==2){
                    iterator.remove();
                    Rabbit.deathRabbit++;
                }
            }
        }
    }

    public void eat(Plant plant){
        if(plant.getWeight()>450 && this.isEat == false && this.isReproduce == false && this.isMove == false){
            plant.setWeight(plant.getWeight()-450);
            this.satiety=450;
            this.stepDeath =0;
            this.isEat = true;
        }else {
            this.isEat = false;
            this.satiety -= 227;
            if(this.satiety<0)stepDeath++;
        }
    }

    @Override
    public ArrayList<Rabbit> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Rabbit> newRabbit = new ArrayList<>();
        int randomLengthRabbit = ThreadLocalRandom.current().nextInt(4);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthRabbit;k++){
                newRabbit.add(new Rabbit());
                Rabbit.newRabbit++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety -= 227;
        if(this.satiety<0)stepDeath++;
        return newRabbit;
    }

    @Override
    public Rabbit move(Cell[][] cells,int i,int j) {
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(3);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>=0){
                    this.satiety-=227;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Rabbit> MoveToOtherRabbit = cells[i][iStepToLeft].getRabbit();
                    MoveToOtherRabbit.add(this);
                    return this;
                }else{
                    Rabbit.deathRabbit++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>=0){
                    this.satiety-=227;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Rabbit> MoveToOtherRabbit = cells[iStepToUp][j].getRabbit();
                    MoveToOtherRabbit.add(this);
                    return this;
                }else{
                    Rabbit.deathRabbit++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety-=227;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Rabbit> MoveToOtherRabbit = cells[i][iStepToRight].getRabbit();
                    MoveToOtherRabbit.add(this);
                    return this;
                }else{
                    Rabbit.deathRabbit++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety-=227;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Rabbit> MoveToOtherRabbit = cells[iStepToDown][j].getRabbit();
                    MoveToOtherRabbit.add(this);
                    return this;
                }else{
                    Rabbit.deathRabbit++;
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
