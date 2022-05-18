package edem;

import edem.herbivores.*;
import edem.predator.*;
import entity.Cell;

import java.util.concurrent.*;

public class Edem {

    static ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
    //static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void runEdem(Cell[][] cells){
        //Травоядные----------------------------------------------------------------------------------------------------
            // растения
        scheduledPool.scheduleAtFixedRate(new EdemPlants(cells),0,1100, TimeUnit.MILLISECONDS);
            //гусеницы
//        executorService.execute(new EdemCaterpillar(cells));
//            //Утки
//        //executorService.execute(new EdemDuck(cells));
//            //Коровы
//        executorService.execute(new EdemCow(cells));
//            //Олени
//        executorService.execute(new EdemDeer(cells));
//            //Козы
//        executorService.execute(new EdemGoat(cells));
//            //Хомяки
//        executorService.execute(new EdemHamster(cells));
//            //Лошади
//        executorService.execute(new EdemHorse(cells));
//            //Кенгуру
//        executorService.execute(new EdemKangaroo(cells));
//            //Кролики
//        executorService.execute(new EdemRabbit(cells));
//            //Овцы
//        executorService.execute(new EdemSheep(cells));
//        //Хищники-------------------------------------------------------------------------------------------------------
//            //Медведи
//        executorService.execute(new EdemBear(cells));
//            //Орлы
//        executorService.execute(new EdemEagle(cells));
//            //Лисы
//        executorService.execute(new EdemFox(cells));
//            //Змеи
//        executorService.execute(new EdemSnake(cells));
//            //Волки
//        executorService.execute(new EdemWolf(cells));
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                generatorAnimal(cells[i][j],700);
            }
        }
    }
    public static void shutdown(){
        scheduledPool.shutdown();
        //executorService.shutdown();
    }

    public static void generatorAnimal(Cell cell, int score){
        for (int i = 0; i < score; i++) {
            int numberOfAnimal = ThreadLocalRandom.current().nextInt();

            if(numberOfAnimal == 0){
                //caterpillar
            }else if(numberOfAnimal == 1){
                //duck
            }else if(numberOfAnimal == 2){
                //ox
            }else if(numberOfAnimal == 3){
                //кабан
            }else if(numberOfAnimal == 4){
                //sheep
            }else if(numberOfAnimal == 5){
                //goat
            }else if(numberOfAnimal == 6){
                //mouce
            }else if(numberOfAnimal == 7){
                //rabbit
            }else if(numberOfAnimal == 8){
                //deer
            }else if(numberOfAnimal == 9){
                //horse
            }else if(numberOfAnimal == 10){
                //eagle
            }else if(numberOfAnimal == 11){
                //bear
            }else if(numberOfAnimal == 12){
                //fox
            }else if(numberOfAnimal == 13){
                //snake
            }else if(numberOfAnimal == 14){
                //wolf
            }

        }
    }

}
