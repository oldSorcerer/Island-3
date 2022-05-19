package entity.herbivores;

import entity.Cell;
import entity.Plant;
import world.LifeStep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivores {
    public static final double MAX_EAT_UP = 0.15;
    public static final int MAX_DEATH = 4;
    public static int newDuck= 0;
    public static int deathDuck = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name = new String();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duck duck = (Duck) o;
        return Objects.equals(name, duck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Duck() {
        weight = 1;
        stepDeath = 0;
        name = Integer.toString(newDuck);
        satiety = 0;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + name + '\'' +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

//    public static void life(Cell[][] cells, int i, int j){
//        ArrayList<Caterpillar> caterpillar =  cells[i][j].getCaterpillar();
//        ArrayList<Duck> duck = cells[i][j].getDuck();
//        List<Plant> plant = cells[i][j].getSynchronizedPlant();
//        //утки едят-------------------------------------------------------------------------------------------------
//        // переменная для храниения гусениц которым не хватило еды (1 растение = 7 уток)
//        //                                                          15 гусениц = 1 утка
//        int hungerDuck = 0;
//        //переменная lifeCyclestep отвечает за то чтобы животные ели или размножались
//        //рандомно выбираем что поесть
//        //0 - растения
//        //1 - гусеницы
//        int choiceFood = ThreadLocalRandom.current().nextInt(2);
//        int amountFood =0;
//        boolean eatCaterpillar =false;
//        boolean eatPlant =false;
//        if(choiceFood==0){
//            amountFood = plant.size();
//            eatPlant = true;
//        }
//        else if(choiceFood == 1){
//            amountFood = caterpillar.size();
//            eatCaterpillar = true;
//        }
//        if(amountFood != 0 && LifeStep.lifeStep % 2 == 0){
//            int eatFood = 0;
//            if(eatPlant){
//                if(duck.size()<=7){
//                    eatFood =1;
//                }else{
//                    eatFood = duck.size()/7;
//                    // если у уток есть остаток, то считаю что этот остаток съедает одно растение
//                    if(caterpillar.size()%7>0) eatFood++;
//                }
//                int x=0;
//                boolean finishPlants = false;
//                for (int k = 0; k < eatFood; k++) {
//                    plant.remove(0);
//                    Plant.deathPlants++;
//                    x++;
//                    if(plant.size()==0){
//                        finishPlants = true;
//                        break;
//                    }
//                }
//                if(finishPlants){
//                    hungerDuck = eatFood-x;
//                }
//                if(finishPlants == false){
//                    for (int k = 0; k < duck.size(); k++) {
//                        duck.get(k).satiety = 0.15;
//
//                    }
//                }else{
//                    for (int k = 0; k < x*15; k++) {
//                        duck.get(k).satiety = 0.15;
//
//                    }
//                }
//            }else if(eatCaterpillar){
//
//                eatFood = caterpillar.size()/15;
//                int x=0;
//                boolean finishEats = false;
//                for (int k = 0; k < eatFood*15; k++) {
//                    caterpillar.remove(0);
//                    Caterpillar.deathGaterpillar++;
//                    x++;
//                    if(plant.size()==0){
//                        finishEats = true;
//                        break;
//                    }
//                }
//                if(finishEats){
//                    hungerDuck = eatFood-x;
//                }
//                if(finishEats == false){
//                    for (int k = 0; k < duck.size(); k++) {
//                        duck.get(k).satiety = 0.15;
//
//                    }
//                }else{
//                    for (int k = 0; k < x/15; k++) {
//                        duck.get(k).satiety = 0.15;
//
//                    }
//                }
//            }
//        //--------------------------------------------------------------------------------------------------------------
//        //размножаемся--------------------------------------------------------------------------------------------------
//        }else if(LifeStep.lifeStep%2==1){
//            //каждая самка откладывает по 5 яйц, предположим что половина текущей популяции самки
//            int newDuck = (duck.size()/2)*5;
//            for (int k = 0; k < 0; k++) {
//                if(Cell.MAX_DUCK>duck.size()){
//                    duck.add(new Duck());
//                    Duck.newDuck++;
//                }
//                duck.get(k).stepDeath++;
//            }
//        //--------------------------------------------------------------------------------------------------------------
//        //передвижение произходит по строке, все движутся к концустроки
//        }else {
//            int step = ThreadLocalRandom.current().nextInt(2);
//            step++;
//            Iterator<Duck> iterator = duck.iterator();
//            Duck duck1 = null;
//            while (iterator.hasNext()){
//                duck1 = iterator.next();
//                System.out.println(duck1);
//                if(duck1.satiety<0.12){
//                    System.out.println("move " + duck1);
//                    iterator.remove();
//                    if(j+step>cells[0].length-1){
//                        cells[i][cells[0].length-1].getDuck().add(duck1);
//                    }else{
//                        cells[i][j+step].getDuck().add(duck1);
//                    }
//                }
//            }
//
//        }
//        Iterator<Duck> iterator1 = duck.iterator();
//        while (iterator1.hasNext()){
//            Duck duck2 = iterator1.next();
//            if(duck2.stepDeath==4){
//                iterator1.remove();
//                Duck.deathDuck++;
//            }
//            if(Math.abs(duck2.satiety-0.15)<= 0.01) duck2.stepDeath++;
//            duck2.satiety -=0.03;
//
//
//        }
//
//    }
    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}

//ArrayList<Caterpillar> caterpillar =  cells[i][j].getCaterpillar();
//                    ArrayList<Duck> duck = cells[i][j].getDuck();
//                    List<Plant> plant = cells[i][j].getSynchronizedPlant();
//                    //утки едят-------------------------------------------------------------------------------------------------
//                    // переменная для храниения гусениц которым не хватило еды (1 растение = 7 уток)
//                    //                                                          15 гусениц = 1 утка
//                    int hungerDuck = 0;
//                    //переменная lifeCyclestep отвечает за то чтобы животные ели или размножались
//                    //рандомно выбираем что поесть
//                    //0 - растения
//                    //1 - гусеницы
//                    int choiceFood = ThreadLocalRandom.current().nextInt(2);
//                    int amountFood =0;
//                    boolean eatCaterpillar =false;
//                    boolean eatPlant =false;
//                    if(choiceFood==0){
//                        amountFood = plant.size();
//                        eatPlant = true;
//                    }
//                    else if(choiceFood == 1){
//                        amountFood = caterpillar.size();
//                        eatCaterpillar = true;
//                    }
//                    if(amountFood != 0 && step % 2 == 0){
//                        int eatFood = 0;
//                        if(eatPlant){
//                            if(duck.size()<=7){
//                                eatFood =1;
//                            }else{
//                                eatFood = duck.size()/7;
//                                // если у уток есть остаток, то считаю что этот остаток съедает одно растение
//                                if(caterpillar.size()%7>0) eatFood++;
//                            }
//                            int x=0;
//                            boolean finishPlants = false;
//                            for (int k = 0; k < eatFood; k++) {
//                                plant.remove(0);
//                                Plant.deathPlants++;
//                                x++;
//                                if(plant.size()==0){
//                                    finishPlants = true;
//                                    break;
//                                }
//                            }
//                            if(finishPlants){
//                                hungerDuck = eatFood-x;
//                            }
//                            if(finishPlants == false){
//                                for (int k = 0; k < duck.size(); k++) {
//                                    duck.get(k).satiety = 0.15;
//
//                                }
//                            }else{
//                                for (int k = 0; k < x*15; k++) {
//                                    duck.get(k).satiety = 0.15;
//
//                                }
//                            }
//                        }else if(eatCaterpillar){
//
//                            eatFood = caterpillar.size()/15;
//                            int x=0;
//                            boolean finishEats = false;
//                            for (int k = 0; k < eatFood*15; k++) {
//                                caterpillar.remove(0);
//                                Caterpillar.deathGaterpillar++;
//                                x++;
//                                if(plant.size()==0){
//                                    finishEats = true;
//                                    break;
//                                }
//                            }
//                            if(finishEats){
//                                hungerDuck = eatFood-x;
//                            }
//                            if(finishEats == false){
//                                for (int k = 0; k < duck.size(); k++) {
//                                    duck.get(k).satiety = 0.15;
//
//                                }
//                            }else{
//                                for (int k = 0; k < x/15; k++) {
//                                    duck.get(k).satiety = 0.15;
//
//                                }
//                            }
//                        }
//                        //--------------------------------------------------------------------------------------------------------------
//                        //размножаемся--------------------------------------------------------------------------------------------------
//                    }else if(step%2==1){
//                        //каждая самка откладывает по 5 яйц, предположим что половина текущей популяции самки
//                        int newDuck = (duck.size()/2)*5;
//                        for (int k = 0; k < 0; k++) {
//                            if(Cell.MAX_DUCK>duck.size()){
//                                duck.add(new Duck());
//                                Duck.newDuck++;
//                            }
//                            duck.get(k).stepDeath++;
//                        }
//                        //--------------------------------------------------------------------------------------------------------------
//                        //передвижение произходит по строке, все движутся к концустроки
//                    }else {
//                        ArrayList<Duck> duckCopy = new ArrayList<>(duck);;
//                        int step1 = ThreadLocalRandom.current().nextInt(2);
//                        step1++;
//                        for (int k = 0; k < duckCopy.size(); k++) {
//                            Duck duckC = duckCopy.get(k);
//                            if(duckC.satiety<0.12){
//                                duck.remove(duckC);
//                                if(j+step>cells[0].length-1){
//                                    cells[i][cells[0].length-1].getDuck().add(duckC);
//                                }else{
//                                    cells[i][j+step1].getDuck().add(duckC);
//                                }
//                            }
//                        }
//                        //Iterator<Duck> iterator = duck.iterator();
//                        //Duck duck1 = null;
////                        while (iterator.hasNext()){
////                            duck1 = iterator.next();
////                           // System.out.println(duck1);
////                            if(duck1.satiety<0.12){
////                               // System.out.println("move " + duck1);
////                                iterator.remove();
////                                if(j+step>cells[0].length-1){
////                                    cells[i][cells[0].length-1].getDuck().add(duck1);
////                                }else{
////                                    cells[i][j+step1].getDuck().add(duck1);
////                                }
////                            }
////                        }
//
//                    }
//
//                    ArrayList<Duck> duckCopy = new ArrayList<>(duck);
//                    for (int k = 0; k < duckCopy.size(); k++) {
//                        Duck duckC = duckCopy.get(k);
//                        if(duckC.stepDeath==4){
//                            duck.remove(duckC);
//                            Duck.deathDuck++;
//                        }
//                    }
//
//                    for (int k = 0; k < duck.size(); k++) {
//                        duck.get(k).stepDeath++;
//                        duck.get(k).satiety-=0.03;
//                        if(duck.get(k).satiety<0.03) duck.get(k).satiety=0;
//                        //System.out.println(duck.get(k));
//                    }