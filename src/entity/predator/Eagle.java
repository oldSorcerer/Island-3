package entity.predator;

import entity.Cell;
import entity.herbivores.*;
import world.Properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator {
    public static int newEagle = 0;
    public static int deathEagle = 0;

    public Eagle() {
        weight = Properties.WEIGHT_EAGLE;
        stepDeath = 0;
        name = Integer.toString(newEagle);
        satiety = Properties.SATIETY_EAGLE;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eagle eagle = (Eagle) o;
        return Objects.equals(name, eagle.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Eagle{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j) {
        ArrayList<Eagle> eagles = cells[i][j].getEagle();
        ArrayList<Eagle> newEagle = new ArrayList<>();
        ArrayList<Eagle> arrayListEagleNeedToDelete = new ArrayList<>();
        for (int k = 0; k < eagles.size(); k++) {
            Eagle eagle = eagles.get(k);
            //выбираем что кушаем
            //0-duck
            //1-mouse
            //2-rabbit
            //3-fox
            int randomFood = ThreadLocalRandom.current().nextInt(3);
            if (randomFood == 0) {
                ArrayList<Duck> ducks = cells[i][j].getDuck();
                eagle.eat(ducks);
            } else if (randomFood == 1) {
                ArrayList<Mouse> mouses = cells[i][j].getMouse();
                eagle.eat(mouses);
            } else if (randomFood == 2) {
                ArrayList<Rabbit> rabbits = cells[i][j].getRabbit();
                eagle.eat(rabbits);
            } else if (randomFood == 3) {
                ArrayList<Fox> foxes = cells[i][j].getFox();
                eagle.eat(foxes);
            }
            //размножаемся
            newEagle.addAll(eagle.reproduce(cells, i, j));
//
            //передвижение
            Eagle eagleMove = eagle.move(cells, i, j);
            if (eagleMove != null) {
                arrayListEagleNeedToDelete.add(eagleMove);
            }
//
        }
        eagles.addAll(newEagle);
        for (Eagle eagle : arrayListEagleNeedToDelete) {
            eagles.remove(eagle);
        }
//
        int needToKill = 0;
        Iterator<Eagle> iterator = eagles.iterator();
        if (eagles.size() > Properties.MAX_EAGLE) {
            needToKill = Math.abs(eagles.size() - Properties.MAX_EAGLE);
            while (needToKill > 0) {
                iterator.next();
                iterator.remove();
                Eagle.deathEagle++;
                needToKill--;
            }
        } else {
            while (iterator.hasNext()) {
                Eagle eagle = iterator.next();
                if (eagle.stepDeath == 3) {
                    iterator.remove();
                    Eagle.deathEagle++;
                }
            }
        }
    }

    public <T> void eat(ArrayList<T> animal) {
        Object object = null;
        //System.out.println("eat Animal");
        if (animal.size() > 0) {
            object = animal.get(0);
            if (object instanceof Duck) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 80)) {
                    Iterator<T> iterator = animal.iterator();
                    int needToEat = 1;
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Duck.deathDuck++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_EAGLE;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Mouse) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 20;
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 90)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Mouse.deathMouse++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_EAGLE;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Rabbit) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 90)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Rabbit.deathRabbit++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_EAGLE;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Fox) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 1;
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 10)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Rabbit.deathRabbit++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_EAGLE;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
                    if (this.satiety < 0) stepDeath++;
                }
            }
        } else {
            this.isEat = false;
            this.satiety -= 350;
            if (this.satiety < 0) stepDeath++;
        }
    }

    @Override
    public ArrayList<Eagle> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Eagle> newEagle = new ArrayList<>();
        int randomLengthEagle = ThreadLocalRandom.current().nextInt(2);
        if (this.isReproduce == false && this.isEat == false && this.isMove == false) {
            for (int k = 0; k < randomLengthEagle; k++) {
                newEagle.add(new Eagle());
                Eagle.newEagle++;
            }
            this.isReproduce = true;
        } else {
            this.isReproduce = false;
        }
        this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
        ;
        if (this.satiety < 0) stepDeath++;
        return newEagle;
    }

    @Override
    public Eagle move(Cell[][] cells, int i, int j) {
        if (this.isEat == false && this.isReproduce == false && this.isMove == false) {
            int randomStepLength = ThreadLocalRandom.current().nextInt(4);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove = true;
            if (randomWay == 0) {
                //west(left)
                int iStepToLeft = j - randomStepLength;
                if (iStepToLeft >= 0) {
                    this.satiety -= 350;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Eagle> MoveToOtherEagle = cells[i][iStepToLeft].getEagle();
                    MoveToOtherEagle.add(this);
                    return this;
                } else {
                    Eagle.deathEagle++;
                    return this;
                }
            } else if (randomWay == 1) {
                //north(up)
                int iStepToUp = i - randomStepLength;
                if (iStepToUp >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Eagle> MoveToOtherEagle = cells[iStepToUp][j].getEagle();
                    MoveToOtherEagle.add(this);
                    return this;
                } else {
                    Eagle.deathEagle++;
                    return this;
                }
            } else if (randomWay == 2) {
                //east(right)
                int iStepToRight = j + randomStepLength;
                if (iStepToRight < cells[0].length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Eagle> MoveToOtherEagle = cells[i][iStepToRight].getEagle();
                    MoveToOtherEagle.add(this);
                    return this;
                } else {
                    Eagle.deathEagle++;
                    return this;
                }
            } else if (randomWay == 3) {
                //south(down)
                int iStepToDown = i + randomStepLength;
                if (iStepToDown < cells.length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_EAGLE;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Eagle> MoveToOtherEagle = cells[iStepToDown][j].getEagle();
                    MoveToOtherEagle.add(this);
                    return this;
                } else {
                    Eagle.deathEagle++;
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
