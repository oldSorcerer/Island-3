package entity.herbivores;

import entity.Cell;
import entity.Plant;
import world.LifeCycle;
import world.LifeStep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivores {
    // гусеница может скущать за раз 0.0025 кг = (округлил) 3 грамм
    public static final int MAX_EAT_UP = 3;

    public static int newGaterpillar = 0;
    public static int deathGaterpillar = 0;


    public Caterpillar() {
        weight = 10;
        satiety = 3;
    }
    //гусеницы питаются в методее жизнь, потому что едят(400 гусениц съедают 1 растение)
    @Override
    public void eat() {

    }
    public static void life(Cell[][] cells, int i, int j) {
        ArrayList<Caterpillar> caterpillar =  cells[i][j].getCaterpillar();
        List<Plant> plant = cells[i][j].getSynchronizedPlant();
        //гусеницы едят-------------------------------------------------------------------------------------------------
        // переменная для храниения гусениц которым не хватило еды (1 = 400 гусениц)
        int hungerCaterpillar = 0;
        //переменная lifeCyclestep отвечает за то чтобы животные ели или размножались
        if(plant.size() != 0 && LifeStep.lifeStep % 2 == 0){
            int eatPlants = 0;
            // 400 гусениц съедают одно растение
            if(caterpillar.size()<=400){
                eatPlants =1;
            }else{
                eatPlants = caterpillar.size()/400;
                // если у гусениц есть остаток, то считаю что этот остаток съедает одно растение
                if(caterpillar.size()%400>0) eatPlants++;
            }
            int x=0;
            boolean finishPlants = false;
            for (int k = 0; k < eatPlants; k++) {
                plant.remove(0);
                Plant.deathPlants++;
                x++;
                if(plant.size()==0){
                    finishPlants = true;
                    break;
                }
            }
            if(finishPlants){
                hungerCaterpillar = eatPlants-x;
            }
            if(finishPlants == false){
                for (int k = 0; k < caterpillar.size(); k++) {
                    caterpillar.get(k).satiety = 0.0025;
                }
            }else{
                for (int k = 0; k < x*400; k++) {
                    caterpillar.get(k).satiety = 0.0025;
                }
            }
        //--------------------------------------------------------------------------------------------------------------
        //размножаемся--------------------------------------------------------------------------------------------------
        }else if(LifeStep.lifeStep%2==1){
            //каждая самка откладывает по 10 яйц, предположим что половина текущей популяции самки
            int newCaterpillar = (caterpillar.size()/2)*10;
            for (int k = 0; k < newCaterpillar; k++) {
                if(Cell.MAX_CATERPILLAR>caterpillar.size()){
                    caterpillar.add(new Caterpillar());
                    Caterpillar.newGaterpillar++;
                }
            }
        //--------------------------------------------------------------------------------------------------------------
        //гусеница не переходит на другую клетку, потому что гусенице доступно только 1 день без еды-------------------------------------------------------------------------
        //тогда 1 день гусеница раждается сытая, но все равно 400 гусенец съедают 1 растение
        //на следующий день гусеницы размножаются и теряют один день, вконце дня они умерают
        }
        //проверяем кому надо добавить шаг до смерти--------------------------------------------------------------------
        Iterator<Caterpillar> iterator = caterpillar.iterator();
        while (iterator.hasNext()){
            Caterpillar caterpillar1 = iterator.next();
            if(caterpillar1.stepDeath==1){
                iterator.remove();
                Caterpillar.deathGaterpillar++;
            }
            if(caterpillar1.satiety==0) caterpillar1.stepDeath++;
            caterpillar1.satiety = 0;
        }
    }
    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

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
