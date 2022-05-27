package entity.herbivores;


import entity.Cell;
import entity.Plant;
import world.Properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Herbivores {
    public static int newHorse = 0;
    public static int deathHorse = 0;

    public Horse() {
        weight = Properties.WEIGHT_HORSE;
        stepDeath = 0;
        name = Integer.toString(newHorse);
        satiety = Properties.SATIETY_HORSE;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Objects.equals(name, horse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j) {
        ArrayList<Horse> horses = cells[i][j].getHorse();
        ArrayList<Horse> newHorse = new ArrayList<>();
        ArrayList<Horse> arrayListHorseNeedToDelete = new ArrayList<>();
        for (int k = 0; k < horses.size(); k++) {
            Horse horse = horses.get(k);
            //кушаем
            List<Plant> plants = cells[i][j].getSynchronizedPlant();
            List<Plant> needToDeletePlant = horse.eat(plants);
            for (Plant plant : needToDeletePlant) {
                plants.remove(plant);
            }

            //размножаемся
            newHorse.addAll(horse.reproduce(cells, i, j));

            //передвижение
            Horse horseMove = horse.move(cells, i, j);
            if (horseMove != null) {
                arrayListHorseNeedToDelete.add(horseMove);
            }
        }
        horses.addAll(newHorse);
        for (Horse horse : arrayListHorseNeedToDelete) {
            horses.remove(horse);
        }

        int needToKill = 0;
        Iterator<Horse> iterator = horses.iterator();

        if (horses.size() > Properties.MAX_HORSE) {
            needToKill = Math.abs(horses.size() - Properties.MAX_HORSE);
            while (needToKill > 0) {
                iterator.next();
                iterator.remove();
                Horse.deathHorse++;
                needToKill--;
            }
        } else {
            while (iterator.hasNext()) {
                Horse horse = iterator.next();
                if (horse.stepDeath == 4) {
                    iterator.remove();
                    Horse.deathHorse++;
                }
            }
        }
    }

    public List<Plant> eat(List<Plant> plants) {
        //есть 5 % от своего веса
        List<Plant> needToDelete = new ArrayList<>();
        if (this.isEat == false && this.isReproduce == false && this.isMove == false) {
            int needToEat = 20;
            if (plants.size() > needToEat) {
                for (int i = 0; i < needToEat; i++) {
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
            satiety = Properties.SATIETY_HORSE;
            this.stepDeath = 0;
            this.isEat = true;
        } else {
            this.isEat = false;
            this.satiety -= Properties.SATIETY_STEP_TO_DEATH_HORSE;
            if (this.satiety < 0) stepDeath++;
        }
        return needToDelete;
    }

    @Override
    public ArrayList<Horse> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Horse> newHorse = new ArrayList<>();
        int randomLengthHorse = ThreadLocalRandom.current().nextInt(2);
        if (this.isReproduce == false && this.isEat == false && this.isMove == false) {
            for (int k = 0; k < randomLengthHorse; k++) {
                newHorse.add(new Horse());
                Horse.newHorse++;
            }
            this.isReproduce = true;
        } else {
            this.isReproduce = false;
        }
        this.satiety -= Properties.SATIETY_STEP_TO_DEATH_HORSE;
        if (this.satiety < 0) stepDeath++;
        return newHorse;
    }

    @Override
    public Horse move(Cell[][] cells, int i, int j) {
        if (this.isEat == false && this.isReproduce == false && this.isMove == false) {
            int randomStepLength = ThreadLocalRandom.current().nextInt(5);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove = true;
            if (randomWay == 0) {
                //west(left)
                int iStepToLeft = j - randomStepLength;
                if (iStepToLeft >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_HORSE;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Horse> MoveToOtherHorse = cells[i][iStepToLeft].getHorse();
                    MoveToOtherHorse.add(this);
                    return this;
                } else {
                    Horse.deathHorse++;
                    return this;
                }
            } else if (randomWay == 1) {
                //north(up)
                int iStepToUp = i - randomStepLength;
                if (iStepToUp >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_HORSE;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Horse> MoveToOtherHorse = cells[iStepToUp][j].getHorse();
                    MoveToOtherHorse.add(this);
                    return this;
                } else {
                    Horse.deathHorse++;
                    return this;
                }
            } else if (randomWay == 2) {
                //east(right)
                int iStepToRight = j + randomStepLength;
                if (iStepToRight < cells[0].length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_HORSE;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Horse> MoveToOtherHorse = cells[i][iStepToRight].getHorse();
                    MoveToOtherHorse.add(this);
                    return this;
                } else {
                    Horse.deathHorse++;
                    return this;
                }
            } else if (randomWay == 3) {
                //south(down)
                int iStepToDown = i + randomStepLength;
                if (iStepToDown < cells.length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_HORSE;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Horse> MoveToOtherHorse = cells[iStepToDown][j].getHorse();
                    MoveToOtherHorse.add(this);
                    return this;
                } else {
                    Horse.deathHorse++;
                    return this;
                }
            }
        } else {
            this.isMove = false;
        }
        return null;
    }

    @Override
    public void eat() {

    }

}
