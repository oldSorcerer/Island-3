import edem.Edem;

import edem.EdemPlants;
import entity.Cell;
import entity.Plant;
import entity.herbivores.Caterpillar;
import entity.herbivores.Duck;
import world.Life;
import world.LifeCycle;
import world.LifeStep;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        //begin initial block-------------------------------------------------------------------------------------------
        Cell[][] cells = new Cell[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
        Edem.runEdem(cells);
        //end initial block---------------------------------------------------------------------------------------------
        //begin world life----------------------------------------------------------------------------------------------

        //Life.runLife(cells);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getDuck().add(new Duck());
                    Duck.newDuck++;
                }
            }
        }
        Thread.sleep(1000);
        int step=1;
        while(step <8){

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ArrayList<Caterpillar> caterpillar =  cells[i][j].getCaterpillar();
                    ArrayList<Duck> duck = cells[i][j].getDuck();
                    List<Plant> plant = cells[i][j].getSynchronizedPlant();
                    //утки едят-------------------------------------------------------------------------------------------------
                    // переменная для храниения гусениц которым не хватило еды (1 растение = 7 уток)
                    //                                                          15 гусениц = 1 утка
                    int hungerDuck = 0;
                    //переменная lifeCyclestep отвечает за то чтобы животные ели или размножались
                    //рандомно выбираем что поесть
                    //0 - растения
                    //1 - гусеницы
                    int choiceFood = ThreadLocalRandom.current().nextInt(2);
                    int amountFood =0;
                    boolean eatCaterpillar =false;
                    boolean eatPlant =false;
                    if(choiceFood==0){
                        amountFood = plant.size();
                        eatPlant = true;
                    }
                    else if(choiceFood == 1){
                        amountFood = caterpillar.size();
                        eatCaterpillar = true;
                    }
                    if(amountFood != 0 && step % 2 == 0){
                        int eatFood = 0;
                        if(eatPlant){
                            if(duck.size()<=7){
                                eatFood =1;
                            }else{
                                eatFood = duck.size()/7;
                                // если у уток есть остаток, то считаю что этот остаток съедает одно растение
                                if(caterpillar.size()%7>0) eatFood++;
                            }
                            int x=0;
                            boolean finishPlants = false;
                            for (int k = 0; k < eatFood; k++) {
                                plant.remove(0);
                                Plant.deathPlants++;
                                x++;
                                if(plant.size()==0){
                                    finishPlants = true;
                                    break;
                                }
                            }
                            if(finishPlants){
                                hungerDuck = eatFood-x;
                            }
                            if(finishPlants == false){
                                for (int k = 0; k < duck.size(); k++) {
                                    duck.get(k).satiety = 0.15;

                                }
                            }else{
                                for (int k = 0; k < x*15; k++) {
                                    duck.get(k).satiety = 0.15;

                                }
                            }
                        }else if(eatCaterpillar){

                            eatFood = caterpillar.size()/15;
                            int x=0;
                            boolean finishEats = false;
                            for (int k = 0; k < eatFood*15; k++) {
                                caterpillar.remove(0);
                                Caterpillar.deathGaterpillar++;
                                x++;
                                if(plant.size()==0){
                                    finishEats = true;
                                    break;
                                }
                            }
                            if(finishEats){
                                hungerDuck = eatFood-x;
                            }
                            if(finishEats == false){
                                for (int k = 0; k < duck.size(); k++) {
                                    duck.get(k).satiety = 0.15;

                                }
                            }else{
                                for (int k = 0; k < x/15; k++) {
                                    duck.get(k).satiety = 0.15;

                                }
                            }
                        }
                        //--------------------------------------------------------------------------------------------------------------
                        //размножаемся--------------------------------------------------------------------------------------------------
                    }else if(step%2==1){
                        //каждая самка откладывает по 5 яйц, предположим что половина текущей популяции самки
                        int newDuck = (duck.size()/2)*5;
                        for (int k = 0; k < 0; k++) {
                            if(Cell.MAX_DUCK>duck.size()){
                                duck.add(new Duck());
                                Duck.newDuck++;
                            }
                            duck.get(k).stepDeath++;
                        }
                        //--------------------------------------------------------------------------------------------------------------
                        //передвижение произходит по строке, все движутся к концустроки
                    }else {
                        ArrayList<Duck> duckCopy = new ArrayList<>(duck);;
                        int step1 = ThreadLocalRandom.current().nextInt(2);
                        step1++;
                        for (int k = 0; k < duckCopy.size(); k++) {
                            Duck duckC = duckCopy.get(k);
                            if(duckC.satiety<0.12){
                                duck.remove(duckC);
                                if(j+step>cells[0].length-1){
                                    cells[i][cells[0].length-1].getDuck().add(duckC);
                                }else{
                                    cells[i][j+step1].getDuck().add(duckC);
                                }
                            }
                        }
                        //Iterator<Duck> iterator = duck.iterator();
                        //Duck duck1 = null;
//                        while (iterator.hasNext()){
//                            duck1 = iterator.next();
//                           // System.out.println(duck1);
//                            if(duck1.satiety<0.12){
//                               // System.out.println("move " + duck1);
//                                iterator.remove();
//                                if(j+step>cells[0].length-1){
//                                    cells[i][cells[0].length-1].getDuck().add(duck1);
//                                }else{
//                                    cells[i][j+step1].getDuck().add(duck1);
//                                }
//                            }
//                        }

                    }

                    ArrayList<Duck> duckCopy = new ArrayList<>(duck);
                    for (int k = 0; k < duckCopy.size(); k++) {
                        Duck duckC = duckCopy.get(k);
                        if(duckC.stepDeath==4){
                            duck.remove(duckC);
                            Duck.deathDuck++;
                        }
                    }

                    for (int k = 0; k < duck.size(); k++) {
                        duck.get(k).stepDeath++;
                        duck.get(k).satiety-=0.03;
                        if(duck.get(k).satiety<0.03) duck.get(k).satiety=0;
                        //System.out.println(duck.get(k));
                    }

                System.out.println("["+(i+1)+", " +(j+1)+"] - "+ cells[i][j]);
                }
                System.out.println(step+"--------------------------------------------------------------------------------------------------------------------------------");
            }
            Thread.sleep(1000);
            step++;
        }

        Edem.shutdown();
        Life.shutdown();
        System.out.println("Caterpillars:"+Caterpillar.newGaterpillar+"/"+Caterpillar.deathGaterpillar+
                            "    Duck:" + Duck.newDuck+"/"+Duck.deathDuck);



    }
}
