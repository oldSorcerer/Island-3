package entity.predator;

import entity.Cell;
import entity.herbivores.Caterpillar;
import entity.herbivores.Duck;
import entity.herbivores.Mouse;
import entity.herbivores.Rabbit;
import world.Properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {
    public static int newFox = 0;
    public static int deathFox = 0;

    public Fox() {
        weight = Properties.WEIGHT_FOX;
        stepDeath = 0;
        name = Integer.toString(newFox);
        satiety = Properties.SATIETY_FOX;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fox fox = (Fox) o;
        return Objects.equals(name, fox.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Fox{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j) {
        ArrayList<Fox> foxes = cells[i][j].getFox();
        ArrayList<Fox> newFox = new ArrayList<>();
        ArrayList<Fox> arrayListFoxNeedToDelete = new ArrayList<>();
        for (int k = 0; k < foxes.size(); k++) {
            Fox fox = foxes.get(k);
            //выбираем что кушаем
            //0-caterpillar
            //1-duck
            //2-mouse
            //3-rabbit
            int randomFood = ThreadLocalRandom.current().nextInt(3);
            if (randomFood == 0) {
                ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
                fox.eat(caterpillars);
            } else if (randomFood == 1) {
                ArrayList<Duck> ducks = cells[i][j].getDuck();
                fox.eat(ducks);
            } else if (randomFood == 2) {
                ArrayList<Mouse> mouse = cells[i][j].getMouse();
                fox.eat(mouse);
            } else if (randomFood == 3) {
                ArrayList<Rabbit> rabbits = cells[i][j].getRabbit();
                fox.eat(rabbits);
            }
            //размножаемся
            newFox.addAll(fox.reproduce(cells, i, j));

            //передвижение
            Fox foxMove = fox.move(cells, i, j);
            if (foxMove != null) {
                arrayListFoxNeedToDelete.add(foxMove);
            }

        }
        foxes.addAll(newFox);
        for (Fox fox : arrayListFoxNeedToDelete) {
            foxes.remove(fox);
        }

        int needToKill = 0;
        Iterator<Fox> iterator = foxes.iterator();
        if (foxes.size() > Properties.MAX_FOX) {
            needToKill = Math.abs(foxes.size() - Properties.MAX_FOX);
            while (needToKill > 0) {
                iterator.next();
                iterator.remove();
                Fox.deathFox++;
                needToKill--;
            }
        } else {
            while (iterator.hasNext()) {
                Fox fox = iterator.next();
                if (fox.stepDeath == 3) {
                    iterator.remove();
                    Fox.deathFox++;
                }
            }
        }
    }

    public <T> void eat(ArrayList<T> animal) {
        Object object = null;
        if (animal.size() > 0) {
            object = animal.get(0);
            if (object instanceof Caterpillar) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 40)) {
                    Iterator<T> iterator = animal.iterator();
                    int needToEat = 20;
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Caterpillar.deathGaterpillar++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_FOX;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Duck) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 2;
                chanceToEat++;
                if (this.isEat == false && this.isReproduce == false && this.isMove == false && (chanceToEat < 60)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        Duck.deathDuck++;
                        needToEat--;
                        if (needToEat == 0) break;
                    }
                    this.satiety = Properties.SATIETY_FOX;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Mouse) {
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 4;
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
                    this.satiety = Properties.SATIETY_FOX;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                }
            } else if (object instanceof Rabbit) {
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
                    this.satiety = Properties.SATIETY_FOX;
                    this.stepDeath = 0;
                    this.isEat = true;
                } else {
                    this.isEat = false;
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                }
            }
        } else {
            this.isEat = false;
            this.satiety -= 1050;
            if (this.satiety < 0) stepDeath++;
        }
    }

    @Override
    public ArrayList<Fox> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Fox> newFox = new ArrayList<>();
        int randomLengthFox = ThreadLocalRandom.current().nextInt(2);
        if (this.isReproduce == false && this.isEat == false && this.isMove == false) {
            for (int k = 0; k < randomLengthFox; k++) {
                newFox.add(new Fox());
                Fox.newFox++;
            }
            this.isReproduce = true;
        } else {
            this.isReproduce = false;
        }
        this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
        ;
        if (this.satiety < 0) stepDeath++;
        return newFox;
    }

    @Override
    public Fox move(Cell[][] cells, int i, int j) {
        if (this.isEat == false && this.isReproduce == false && this.isMove == false) {
            int randomStepLength = ThreadLocalRandom.current().nextInt(3);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove = true;
            if (randomWay == 0) {
                //west(left)
                int iStepToLeft = j - randomStepLength;
                if (iStepToLeft >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Fox> MoveToOtherFox = cells[i][iStepToLeft].getFox();
                    MoveToOtherFox.add(this);
                    return this;
                } else {
                    Fox.deathFox++;
                    return this;
                }
            } else if (randomWay == 1) {
                //north(up)
                int iStepToUp = i - randomStepLength;
                if (iStepToUp >= 0) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Fox> MoveToOtherFox = cells[iStepToUp][j].getFox();
                    MoveToOtherFox.add(this);
                    return this;
                } else {
                    Fox.deathFox++;
                    return this;
                }
            } else if (randomWay == 2) {
                //east(right)
                int iStepToRight = j + randomStepLength;
                if (iStepToRight < cells[0].length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Fox> MoveToOtherFox = cells[i][iStepToRight].getFox();
                    MoveToOtherFox.add(this);
                    return this;
                } else {
                    Fox.deathFox++;
                    return this;
                }
            } else if (randomWay == 3) {
                //south(down)
                int iStepToDown = i + randomStepLength;
                if (iStepToDown < cells.length) {
                    this.satiety -= Properties.SATIETY_STEP_TO_DEATH_FOX;
                    if (this.satiety < 0) stepDeath++;
                    ArrayList<Fox> MoveToOtherFox = cells[iStepToDown][j].getFox();
                    MoveToOtherFox.add(this);
                    return this;
                } else {
                    Fox.deathFox++;
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
