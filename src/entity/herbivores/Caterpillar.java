package entity.herbivores;

import entity.Cell;
import entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivores {
    // гусеница может скущать за раз 0.0025 кг = (округлил) 3 грамм
    public static final int MAX_EAT_UP = 3;

    public static int newGaterpillar = 0;
    public static int deathGaterpillar = 0;
    private boolean isEat = false;
    private boolean isReproduce = false;

    private String name;

    public String getName() {
        return name;
    }

    public Caterpillar() {
        weight = 10;
        satiety = 3;
        isEat = true;
        name = Integer.toString(newGaterpillar);
    }

    public boolean isReproduce() {
        return isReproduce;
    }

    public void setReproduce(boolean reproduce) {
        isReproduce = reproduce;
    }

    public boolean isEat() {
        return isEat;
    }

    public void setEat(boolean eat) {
        isEat = eat;
    }


    //гусеницы питаются в методее жизнь, потому что едят(400 гусениц съедают 1 растение)
    @Override
    public void eat() {
    }

    @Override
    public void move(Cell[][] cells ) {
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
    public void reproduce(Cell[][] cells, int i, int j){
    }

    public ArrayList<Caterpillar> reproduce(Cell[][] cells, int i, int j,boolean f) {
        ArrayList<Caterpillar> newCaterpillar = new ArrayList<>();
        int randomLengthCaterpillar = ThreadLocalRandom.current().nextInt(10);
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

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
        ArrayList<Caterpillar> newCaterpillar = new ArrayList<>();
        for (int k = 0; k < caterpillars.size(); k++) {
            Caterpillar caterpillar = caterpillars.get(k);
            Plant plant = cells[i][j].getSynchronizedPlant().get(ThreadLocalRandom.current().nextInt(cells[i][j].getSynchronizedPlant().size()));
            caterpillar.eat(plant);
            newCaterpillar.addAll(caterpillar.reproduce(cells,i,j,false));
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

    @Override
    public String toString() {
        return "Caterpillar{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }
}
