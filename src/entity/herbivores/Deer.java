package entity.herbivores;

import entity.Cell;
import entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Deer extends Herbivores {
    public static int newDeer= 0;
    public static int deathDeer = 0;
    public Deer() {
        weight = 300_000;
        stepDeath = 0;
        name = Integer.toString(newDeer);
        satiety = 15_000;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deer deer = (Deer) o;
        return Objects.equals(name, deer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Deer{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Deer> deers = cells[i][j].getDeer();
        ArrayList<Deer> newDeer = new ArrayList<>();
        ArrayList<Deer> arrayListDeerNeedToDelete = new ArrayList<>();
        for (int k = 0; k < deers.size(); k++) {
            Deer deer = deers.get(k);
            //кушаем
            List<Plant> plants = cells[i][j].getSynchronizedPlant();
            List<Plant> needToDeletePlant = deer.eat(plants);
            for (Plant plant :needToDeletePlant) {
                plants.remove(plant);
            }

            //размножаемся
            newDeer.addAll(deer.reproduce(cells,i,j));

            //передвижение
            Deer deerMove = deer.move(cells,i,j);
            if(deerMove != null){
                arrayListDeerNeedToDelete.add(deerMove);
            }
        }
        deers.addAll(newDeer);
        for (Deer deer: arrayListDeerNeedToDelete) {
            deers.remove(deer);
        }

        int needToKill = 0;
        Iterator<Deer> iterator = deers.iterator();

        if(deers.size()>20){
            needToKill = Math.abs(deers.size()-20);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Deer.deathDeer++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Deer deer= iterator.next();
                if(deer.stepDeath==4){
                    iterator.remove();
                    Deer.deathDeer++;
                }
            }
        }
    }

    public List<Plant> eat(List<Plant> plants){
        //есть 5 % от своего веса
        List<Plant> needToDelete = new ArrayList<>();
        if(this.isEat == false && this.isReproduce == false  && this.isMove == false) {
            int needToEat = 15;
            if(plants.size()>needToEat){
                for (int i=0;i<needToEat;i++) {
                    needToDelete.add(plants.get(i));
                    Plant.deathPlants++;
                }
            }
//            while (iterator.hasNext()){
//                iterator.next();
//                iterator.remove();
//                Plant.deathPlants++;
//                needToEat--;
//                if(needToEat ==0)break;
//            }
            this.satiety = 15_000;
            this.stepDeath = 0;
            this.isEat = true;
        }else{
            this.isEat = false;
            this.satiety -= 3750;
            if(this.satiety<0)stepDeath++;
        }
        return needToDelete;
    }

    @Override
    public ArrayList<Deer> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Deer> newDeer= new ArrayList<>();
        int randomLengthDeer = ThreadLocalRandom.current().nextInt(2);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthDeer;k++){
                newDeer.add(new Deer());
                Deer.newDeer++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety-=3750;;
        if(this.satiety<0)stepDeath++;
        return newDeer;
    }

    public Deer move(Cell[][] cells,int i,int j) {
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(5);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>=0){
                    this.satiety-=3750;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Deer> MoveToOtherDeer = cells[i][iStepToLeft].getDeer();
                    MoveToOtherDeer.add(this);
                    return this;
                }else{
                    Deer.deathDeer++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>=0){
                    this.satiety -= 3750;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Deer> MoveToOtherDeer = cells[iStepToUp][j].getDeer();
                    MoveToOtherDeer.add(this);
                    return this;
                }else{
                    Deer.deathDeer++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety -= 3750;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Deer> MoveToOtherDeer = cells[i][iStepToRight].getDeer();
                    MoveToOtherDeer.add(this);
                    return this;
                }else{
                    Deer.deathDeer++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety -= 3750;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Deer> MoveToOtherDeer = cells[iStepToDown][j].getDeer();
                    MoveToOtherDeer.add(this);
                    return this;
                }else{
                    Deer.deathDeer++;
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
