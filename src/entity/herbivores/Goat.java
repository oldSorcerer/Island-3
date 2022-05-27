package entity.herbivores;

import entity.Cell;
import entity.Plant;
import world.Properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Goat extends Herbivores {
    public static int newGoat = 0;
    public static int deathGoat = 0;

    public Goat() {
        weight = Properties.WEIGHT_GOAT;
        stepDeath = 0;
        name = Integer.toString(newGoat);
        satiety = Properties.SATIETY_GOAT;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goat goat = (Goat) o;
        return Objects.equals(name, goat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Goat{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j) {
        ArrayList<Goat> goats = cells[i][j].getGoat();
        ArrayList<Goat> newGoat = new ArrayList<>();
        ArrayList<Goat> arrayListGoatNeedToDelete = new ArrayList<>();
        for (int k = 0; k < goats.size(); k++) {
            Goat goat = goats.get(k);
            //кушаем
            List<Plant> plants = cells[i][j].getSynchronizedPlant();
            List<Plant> needToDeletePlant = goat.eat(plants);
            for (Plant plant : needToDeletePlant) {
                plants.remove(plant);
            }

            //размножаемся
            newGoat.addAll(goat.reproduce(cells, i, j));

            //передвижение
            Goat goatMove = goat.move(cells, i, j);
            if (goatMove != null) {
                arrayListGoatNeedToDelete.add(goatMove);
            }

        }
        goats.addAll(newGoat);
        for (Goat goat : arrayListGoatNeedToDelete) {
            goats.remove(goat);
        }

        int needToKill = 0;
        Iterator<Goat> iterator = goats.iterator();

        if (goats.size() > Properties.MAX_GOAT) {
            needToKill = Math.abs(goats.size() - Properties.MAX_GOAT);
            while (needToKill > 0) {
                iterator.next();
                iterator.remove();
                Goat.deathGoat++;
                needToKill--;
            }
        } else {
            while (iterator.hasNext()) {
                Goat goat = iterator.next();
                if (goat.stepDeath == 3) {
                    iterator.remove();
                    Goat.deathGoat++;
                }
            }
        }
    }

    public List<Plant> eat(List<Plant> plants) {
        //если он даже немного поел ставлю ему поленое насыщение иначе он вымирает
        List<Plant> needToDelete = new ArrayList<>();
        if (this.isEat == false && this.isReproduce == false && this.isMove == false) {
            int needToEat = 10;
            if (plants.size() > needToEat) {
                for (int i = 0; i < needToEat; i++) {
                    needToDelete.add(plants.get(i));
                    Plant.deathPlants++;
                }
            }
            this.satiety = Properties.SATIETY_GOAT;
            this.stepDeath = 0;
            this.isEat = true;
        } else {
            this.isEat = false;
            this.satiety -= Properties.SATIETY_STEP_TO_DEATH_GOAT;
            if (this.satiety < 0) stepDeath++;
        }
        return needToDelete;
    }

    @Override
    public ArrayList<Goat> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Goat> newGoat = new ArrayList<>();
        int randomLengthGoat = ThreadLocalRandom.current().nextInt(2);
        if (this.isReproduce == false && this.isEat == false && this.isMove == false) {
            for (int k = 0; k < randomLengthGoat; k++) {
                newGoat.add(new Goat());
                Goat.newGoat++;
            }
            this.isReproduce = true;
        } else {
            this.isReproduce = false;
        }
        this.satiety -= Properties.SATIETY_STEP_TO_DEATH_GOAT;
        if (this.satiety < 0) stepDeath++;
        return newGoat;
    }

    @Override
    public Goat move(Cell[][] cells, int i, int j) {
        if (this.isEat == false && this.isReproduce == false && this.isMove == false) {
            int randomStepLength = ThreadLocalRandom.current().nextInt(4);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove = true;
            if (randomWay == 0) {
                //west(left)
                int iStepToLeft = j - randomStepLength;
                if (iStepToLeft >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_GOAT;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Goat> MoveToOtherGoat = cells[i][iStepToLeft].getGoat();
                    MoveToOtherGoat.add(this);
                    return this;
                } else {
                    Goat.deathGoat++;
                    return this;
                }
            } else if (randomWay == 1) {
                //north(up)
                int iStepToUp = i - randomStepLength;
                if (iStepToUp >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_GOAT;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Goat> MoveToOtherGoat = cells[iStepToUp][j].getGoat();
                    MoveToOtherGoat.add(this);
                    return this;
                } else {
                    Goat.deathGoat++;
                    return this;
                }
            } else if (randomWay == 2) {
                //east(right)
                int iStepToRight = j + randomStepLength;
                if (iStepToRight < cells[0].length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_GOAT;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Goat> MoveToOtherGoat = cells[i][iStepToRight].getGoat();
                    MoveToOtherGoat.add(this);
                    return this;
                } else {
                    Goat.deathGoat++;
                    return this;
                }
            } else if (randomWay == 3) {
                //south(down)
                int iStepToDown = i + randomStepLength;
                if (iStepToDown < cells.length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_GOAT;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Goat> MoveToOtherGoat = cells[iStepToDown][j].getGoat();
                    MoveToOtherGoat.add(this);
                    return this;
                } else {
                    Goat.deathGoat++;
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
