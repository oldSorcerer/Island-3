package entity.predator;

import entity.Cell;
import entity.herbivores.*;
import world.Properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Predator {
    public static int newBoa = 0;
    public static int deathBoa = 0;

    public Boa() {
        weight = Properties.WEIGHT_BOA;
        stepDeath = 0;
        name = Integer.toString(newBoa);
        satiety = Properties.SATIETY_BOA;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boa boa = (Boa) o;
        return Objects.equals(name, boa.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Boa{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j) {
        ArrayList<Boa> boas = cells[i][j].getBoa();
        ArrayList<Boa> newBoa = new ArrayList<>();
        ArrayList<Boa> arrayListBoaNeedToDelete = new ArrayList<>();
        for (int k = 0; k < boas.size(); k++) {
            Boa boa = boas.get(k);
            //выбираем что кушаем
            //0-duck
            //1-mouse
            //2-rabbit
            //3-fox
            int randomFood = ThreadLocalRandom.current().nextInt(3);
            if (randomFood == 0) {
                ArrayList<Duck> ducks = cells[i][j].getDuck();
                boa.eat(ducks);
            } else if (randomFood == 1) {
                ArrayList<Mouse> mouses = cells[i][j].getMouse();
                boa.eat(mouses);
            } else if (randomFood == 2) {
                ArrayList<Rabbit> rabbits = cells[i][j].getRabbit();
                boa.eat(rabbits);
            } else if (randomFood == 3) {
                ArrayList<Fox> foxes = cells[i][j].getFox();
                boa.eat(foxes);
            }
            //размножаемся
            newBoa.addAll(boa.reproduce(cells, i, j));
//
            //передвижение
            Boa boaMove = boa.move(cells, i, j);
            if (boaMove != null) {
                arrayListBoaNeedToDelete.add(boaMove);
            }
//
        }
        boas.addAll(newBoa);
        for (Boa boa : arrayListBoaNeedToDelete) {
            boas.remove(boa);
        }
//
        int needToKill = 0;
        Iterator<Boa> iterator = boas.iterator();
        if (boas.size() > Properties.MAX_BOA) {
            needToKill = Math.abs(boas.size() - Properties.MAX_BOA);
            while (needToKill > 0) {
                iterator.next();
                iterator.remove();
                Boa.deathBoa++;
                needToKill--;
            }
        } else {
            while (iterator.hasNext()) {
                Boa boa = iterator.next();
                if (boa.stepDeath == 1) {
                    iterator.remove();
                    Boa.deathBoa++;
                }
            }
        }
    }

    public <T> void eat(ArrayList<T> animal) {
        Object object = null;
        if (animal.size() > 0) {
            object = animal.get(0);
            if (object instanceof Duck) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 10)) {
                    Iterator<T> iterator = animal.iterator();
                    int needToEat = 3;
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Duck.deathDuck++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_BOA;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Mouse) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 6;
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 40)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Mouse.deathMouse++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_BOA;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Rabbit) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 2;
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 20)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Rabbit.deathRabbit++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_BOA;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Fox) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 15)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Fox.deathFox++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_BOA;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                }
            }
        } else {
            this.isEat = false;
            this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
            if (this.satiety < 0) stepDeath++;
        }
    }

    @Override
    public ArrayList<Boa> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Boa> newBoa = new ArrayList<>();
        int randomLengthBoa = ThreadLocalRandom.current().nextInt(2);
        if (this.isReproduce == false && this.isEat == false && this.isMove == false) {
            for (int k = 0; k < randomLengthBoa; k++) {
                newBoa.add(new Boa());
                Boa.newBoa++;
            }
            this.isReproduce = true;
        } else {
            this.isReproduce = false;
        }
        this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
        ;
        if (this.satiety < 0) stepDeath++;
        return newBoa;
    }

    @Override
    public Boa move(Cell[][] cells, int i, int j) {
        if (this.isEat == false && this.isReproduce == false && this.isMove == false) {
            int randomStepLength = ThreadLocalRandom.current().nextInt(2);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove = true;
            if (randomWay == 0) {
                //west(left)
                int iStepToLeft = j - randomStepLength;
                if (iStepToLeft >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Boa> MoveToOtherBoa = cells[i][iStepToLeft].getBoa();
                    MoveToOtherBoa.add(this);
                    return this;
                } else {
                    Boa.deathBoa++;
                    return this;
                }
            } else if (randomWay == 1) {
                //north(up)
                int iStepToUp = i - randomStepLength;
                if (iStepToUp >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Boa> MoveToOtherBoa = cells[iStepToUp][j].getBoa();
                    MoveToOtherBoa.add(this);
                    return this;
                } else {
                    Boa.deathBoa++;
                    return this;
                }
            } else if (randomWay == 2) {
                //east(right)
                int iStepToRight = j + randomStepLength;
                if (iStepToRight < cells[0].length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Boa> MoveToOtherBoa = cells[i][iStepToRight].getBoa();
                    MoveToOtherBoa.add(this);
                    return this;
                } else {
                    Boa.deathBoa++;
                    return this;
                }
            } else if (randomWay == 3) {
                //south(down)
                int iStepToDown = i + randomStepLength;
                if (iStepToDown < cells.length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_MAX_BOA;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Boa> MoveToOtherBoa = cells[iStepToDown][j].getBoa();
                    MoveToOtherBoa.add(this);
                    return this;
                } else {
                    Boa.deathBoa++;
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
