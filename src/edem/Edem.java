package edem;

import entity.Cell;
import entity.herbivores.*;
import entity.predator.*;

import java.util.concurrent.*;

public class Edem {

    static ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);

    public static void runEdem(Cell[][] cells){
        scheduledPool.scheduleAtFixedRate(new EdemPlants(cells),0,1100, TimeUnit.MILLISECONDS);

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                generatorAnimal(cells[i][j],700);
            }
        }
    }
    public static void shutdown(){
        scheduledPool.shutdown();
    }

    public static void generatorAnimal(Cell cell, int score){
        for (int i = 0; i < score; i++) {
            int numberOfAnimal = ThreadLocalRandom.current().nextInt(15);
            if(numberOfAnimal == 0){
                //caterpillar
                cell.getCaterpillar().add(new Caterpillar());
            }else if(numberOfAnimal == 1){
                //duck
                cell.getDuck().add(new Duck());
            }else if(numberOfAnimal == 2){
                //Buffalo
                cell.getBuffalo().add(new Buffalo());
            }else if(numberOfAnimal == 3){
                //кабан
                cell.getBoar().add(new Boar());
            }else if(numberOfAnimal == 4){
                //sheep
                cell.getSheep().add(new Sheep());
            }else if(numberOfAnimal == 5){
                //goat
                cell.getGoat().add(new Goat());
            }else if(numberOfAnimal == 6){
                //mouce
                cell.getMouse().add(new Mouse());
            }else if(numberOfAnimal == 7){
                //rabbit
                cell.getRabbit().add(new Rabbit());
            }else if(numberOfAnimal == 8){
                //deer
                cell.getDeer().add(new Deer());
            }else if(numberOfAnimal == 9){
                //horse
                cell.getHorse().add(new Horse());
            }else if(numberOfAnimal == 10){
                //eagle
                cell.getEagle().add(new Eagle());
            }else if(numberOfAnimal == 11){
                //bear
                cell.getBear().add(new Bear());
            }else if(numberOfAnimal == 12){
                //fox
                cell.getFox().add(new Fox());
            }else if(numberOfAnimal == 13){
                //boa
                cell.getBoa().add(new Boa());
            }else if(numberOfAnimal == 14){
                //wolf
                cell.getWolf().add(new Wolf());
            }

        }
    }

}
