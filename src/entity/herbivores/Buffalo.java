package entity.herbivores;

import entity.Cell;
import entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Buffalo extends Herbivores {
    public static int newBuffalo = 0;
    public static int deathBuffalo = 0;

    public Buffalo() {
        weight = 700_000;
        stepDeath = 0;
        name = Integer.toString(newBuffalo);
        satiety = 35_000;
        isEat = false;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buffalo buffalo = (Buffalo) o;
        return Objects.equals(name, buffalo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Buffalo{" +
                "stepToDeath=" + stepToDeath +
                ", satiety=" + satiety +
                ", name='" + name + '\'' +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Buffalo> buffaloes = cells[i][j].getBuffalo();
        ArrayList<Buffalo> newBuffalo = new ArrayList<>();
        ArrayList<Buffalo> arrayListBuffaloNeedToDelete = new ArrayList<>();
        for (int k = 0; k < buffaloes.size(); k++) {
            Buffalo buffalo = buffaloes.get(k);
            //кушаем
            List<Plant> plants = cells[i][j].getSynchronizedPlant();
            List<Plant> needToDeletePlant = buffalo.eat(plants);
            for (Plant plant :needToDeletePlant) {
                plants.remove(plant);
            }

            //размножаемся
            newBuffalo.addAll(buffalo.reproduce(cells,i,j));

            //передвижение
            Buffalo buffaloMove = buffalo.move(cells,i,j);
            if(buffaloMove != null){
                arrayListBuffaloNeedToDelete.add(buffaloMove);
            }

        }
        buffaloes.addAll(newBuffalo);
        for (Buffalo buffalo: arrayListBuffaloNeedToDelete) {
            buffaloes.remove(buffalo);
        }

        int needToKill = 0;
        Iterator<Buffalo> iterator = buffaloes.iterator();

        if(buffaloes.size()>10){
            needToKill = Math.abs(buffaloes.size()-10);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Buffalo.deathBuffalo++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Buffalo buffalo = iterator.next();
                if(buffalo.stepDeath==3){
                    iterator.remove();
                    Buffalo.deathBuffalo++;
                }
            }
        }
    }
    public List<Plant> eat(List<Plant> plants){
        //если он даже немного поел ставлю ему поленое насыщение иначе он вымирает
        //ест 5 % от массы
        List<Plant> needToDelete = new ArrayList<>();
        if(this.isEat == false && this.isReproduce == false  && this.isMove == false) {
            int needToEat = 35;
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
            this.satiety=35_000;
            this.stepDeath =0;
            this.isEat = true;
        }else{
            this.isEat = false;
            this.satiety-=11_700;
            if(this.satiety<0)stepDeath++;
        }
        return needToDelete;
    }
    @Override
    public ArrayList<Buffalo> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Buffalo> newBuffalo = new ArrayList<>();
        int randomLengthCaterpillar = ThreadLocalRandom.current().nextInt(2);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthCaterpillar;k++){
                newBuffalo.add(new Buffalo());
                Buffalo.newBuffalo++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety-=11_700;
        if(this.satiety<0)stepDeath++;
        return newBuffalo;
    }

    @Override
    public Buffalo move(Cell[][] cells,int i,int j) {
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(4);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>=0){
                    this.satiety-=11_700;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Buffalo> MoveToOtherBuffalo = cells[i][iStepToLeft].getBuffalo();
                    MoveToOtherBuffalo.add(this);
                    return this;
                }else{
                    Buffalo.deathBuffalo++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>=0){
                    this.satiety-=11_700;
                    if(this.satiety<0)stepDeath++;

                    ArrayList<Buffalo> MoveToOtherBuffalo = cells[iStepToUp][j].getBuffalo();
                    MoveToOtherBuffalo.add(this);

                    return this;
                }else{

                    Buffalo.deathBuffalo++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety-=11_700;
                    if(this.satiety<0)stepDeath++;

                    ArrayList<Buffalo> MoveToOtherBuffalo = cells[i][iStepToRight].getBuffalo();
                    MoveToOtherBuffalo.add(this);

                    return this;
                }else{

                    Buffalo.deathBuffalo++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety-=11_700;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Buffalo> MoveToOtherBuffalo = cells[iStepToDown][j].getBuffalo();
                    MoveToOtherBuffalo.add(this);
                    return this;
                }else{

                    Buffalo.deathBuffalo++;
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
