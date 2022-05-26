package entity.herbivores;

import entity.Animal;
import entity.Cell;
import entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivores {
    public static int newGaterpillar = 0;
    public static int deathGaterpillar = 0;

    public Caterpillar() {
        weight = 10;
        satiety = 3;
        isEat = true;
        isMove = false;
        isReproduce = false;
        name = Integer.toString(newGaterpillar);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caterpillar caterpillar = (Caterpillar) o;
        return Objects.equals(name, caterpillar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Caterpillar{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
        ArrayList<Caterpillar> newCaterpillar = new ArrayList<>();
        for (int k = 0; k < caterpillars.size(); k++) {
            Caterpillar caterpillar = caterpillars.get(k);
            Plant plant = null;
            if(cells[i][j].getSynchronizedPlant().size()>0){
                plant = cells[i][j].getSynchronizedPlant().get(0);
                caterpillar.eat(plant);
            }

            newCaterpillar.addAll(caterpillar.reproduce(cells,i,j));
        }
        caterpillars.addAll(newCaterpillar);
        int needToKill =0;
        Iterator<Caterpillar> iterator = caterpillars.iterator();
        if(caterpillars.size()>1000){
            needToKill = Math.abs(caterpillars.size()-1000);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Caterpillar.deathGaterpillar++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Caterpillar caterpillar =iterator.next();
                if(caterpillar.satiety==0){
                    iterator.remove();
                    Caterpillar.deathGaterpillar++;
                }
            }
        }
    }

    public void eat(Plant plant){
        if(plant.getWeight()>3 && this.isEat == false && this.isReproduce == false){
            plant.setWeight(plant.getWeight()-3);
            this.satiety=3;
            this.isEat = true;
        }else {
            this.isEat = false;
        }
    }

    public ArrayList<Caterpillar> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Caterpillar> newCaterpillar = new ArrayList<>();
        int randomLengthCaterpillar = ThreadLocalRandom.current().nextInt(50);
        if(this.isReproduce == false && this.isEat == false ){
            for (int k=0;k<randomLengthCaterpillar;k++){
                newCaterpillar.add(new Caterpillar());
                Caterpillar.newGaterpillar++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety=0;
        return newCaterpillar;
    }

    @Override
    public void eat() {
    }

    @Override
    public Animal move(Cell[][] cells, int i, int j) {
        return null;
    }
}
