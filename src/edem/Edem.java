package edem;

import edem.herbivores.*;
import edem.predator.*;
import entity.Cell;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Edem {

    static ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void runEdem(Cell[][] cells){
        //Травоядные----------------------------------------------------------------------------------------------------
            // растения
        scheduledPool.scheduleAtFixedRate(new EdemPlants(cells),0,1100, TimeUnit.MILLISECONDS);
            //гусеницы
        executorService.execute(new EdemCaterpillar(cells));
            //Утки
        executorService.execute(new EdemDuck(cells));
            //Коровы
        executorService.execute(new EdemCow(cells));
            //Олени
        executorService.execute(new EdemDeer(cells));
            //Козы
        executorService.execute(new EdemGoat(cells));
            //Хомяки
        executorService.execute(new EdemHamster(cells));
            //Лошади
        executorService.execute(new EdemHorse(cells));
            //Кенгуру
        executorService.execute(new EdemKangaroo(cells));
            //Кролики
        executorService.execute(new EdemRabbit(cells));
            //Овцы
        executorService.execute(new EdemSheep(cells));
        //Хищники-------------------------------------------------------------------------------------------------------
            //Медведи
        executorService.execute(new EdemBear(cells));
            //Орлы
        executorService.execute(new EdemEagle(cells));
            //Лисы
        executorService.execute(new EdemFox(cells));
            //Змеи
        executorService.execute(new EdemSnake(cells));
            //Волки
        executorService.execute(new EdemWolf(cells));

    }
    public static void shutdown(){
        scheduledPool.shutdown();
        executorService.shutdown();
    }

}
