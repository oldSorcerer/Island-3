package entity.herbivores;

import entity.Cell;
import entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivores {
    public static final int MAX_DEATH = 3;
    public static int newMouse = 0;
    public static int deathMouse= 0;
    public Mouse() {
        weight = 50;
        stepDeath = 0;
        name = Integer.toString(newMouse);
        satiety = 10;
        isEat = false;
        isMove = false;
        isReproduce = false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return Objects.equals(name, mouse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "name='" + name + '\'' +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Mouse> mouses = cells[i][j].getMouse();
        ArrayList<Mouse> newMouse = new ArrayList<>();
        ArrayList<Mouse> arrayListMouseNeedToDelete = new ArrayList<>();
        for (int k = 0; k < mouses.size(); k++) {
            Mouse mouse = mouses.get(k);

            //выбираем что кушаем
            //0-caterpillar
            //1-plant
            int randomFood = ThreadLocalRandom.current().nextInt(2);
            if(randomFood == 0){
                ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
                mouse.eat(caterpillars);
            }else{
                Plant plant = null;
                if(cells[i][j].getSynchronizedPlant().size()>0){
                    plant = cells[i][j].getSynchronizedPlant().get(0);
                    mouse.eat(plant);
                }
            }

            //размножаемся
            newMouse.addAll(mouse.reproduce(cells,i,j));

            //передвижение
            Mouse mouseMove = mouse.move(cells,i,j);
            if(mouseMove != null){
                arrayListMouseNeedToDelete.add(mouseMove);
            }

        }
        mouses.addAll(newMouse);
        for (Mouse mouse: arrayListMouseNeedToDelete) {
            mouses.remove(mouse);
        }

        int needToKill =0;
        Iterator<Mouse> iterator = mouses.iterator();

        if(mouses.size()>500){
            needToKill = Math.abs(mouses.size()-500);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Mouse.deathMouse++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Mouse mouse = iterator.next();
                if(mouse.stepDeath==1){
                    iterator.remove();
                    Mouse.deathMouse++;
                }
            }
        }
    }

    @Override
    public void eat() {

    }
    public void eat(Plant plant){
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            plant.setWeight(plant.getWeight()-10);
            this.satiety=10;
            this.stepDeath =0;
            this.isEat = true;
        }else {
            this.isEat = false;
            this.satiety-=4;
            if(this.satiety<0)stepDeath++;
        }
    }

    public void eat(ArrayList<Caterpillar> caterpillars){
        int chanceToEat = ThreadLocalRandom.current().nextInt(100);
        chanceToEat++;
        if(this.isEat == false && this.isReproduce == false && (chanceToEat<90) && this.isMove == false) {
            Iterator<Caterpillar> iterator = caterpillars.iterator();
            int needToEat = 1;
            while (iterator.hasNext()){
                iterator.next();
                iterator.remove();
                Caterpillar.deathGaterpillar++;
                needToEat--;
                if(needToEat ==0)break;
            }
            this.satiety=10;
            this.stepDeath =0;
            this.isEat = true;
        }else{
            this.isEat = false;
            this.satiety-= 4;
            if(this.satiety<0)stepDeath++;
        }
    }

    @Override
    public ArrayList<Mouse> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Mouse> newMouse= new ArrayList<>();
        int randomLengthCaterpillar = ThreadLocalRandom.current().nextInt(5);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthCaterpillar;k++){
                newMouse.add(new Mouse());
                Mouse.newMouse++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety-=4;
        if(this.satiety<0)stepDeath++;
        return newMouse;
    }

    public Mouse move(Cell[][] cells,int i,int j) {
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(2);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>=0){
                    this.satiety-=4;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Mouse> MoveToOtherMouse = cells[i][iStepToLeft].getMouse();
                    MoveToOtherMouse.add(this);
                    return this;
                }else{
                    Mouse.deathMouse++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>=0){
                    this.satiety-=38;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Mouse> MoveToOtherMouse = cells[iStepToUp][j].getMouse();
                    MoveToOtherMouse.add(this);
                    return this;
                }else{
                    Mouse.deathMouse++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety-=38;
                    if(this.satiety<0)stepDeath++;

                    ArrayList<Mouse> MoveToOtherMouse = cells[i][iStepToRight].getMouse();
                    MoveToOtherMouse.add(this);

                    return this;
                }else{
                    Mouse.deathMouse++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety-=38;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Mouse> MoveToOtherMouse = cells[iStepToDown][j].getMouse();
                    MoveToOtherMouse.add(this);
                    return this;
                }else{
                    Mouse.deathMouse++;
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
}


