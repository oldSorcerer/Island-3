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
                generatorAnimal(cells[i][j],300);
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
                Caterpillar.newGaterpillar++;
            }else if(numberOfAnimal == 1){
                //duck
                cell.getDuck().add(new Duck());
                Duck.newDuck++;
            }else if(numberOfAnimal == 2){
                //Buffalo
                cell.getBuffalo().add(new Buffalo());
                Buffalo.newBuffalo++;
            }else if(numberOfAnimal == 3){
                //кабан
                cell.getBoar().add(new Boar());
                Boar.newBoar++;
            }else if(numberOfAnimal == 4){
                //sheep
                cell.getSheep().add(new Sheep());
                Sheep.newSheep++;
            }else if(numberOfAnimal == 5){
                //goat
                cell.getGoat().add(new Goat());
                Goat.newGoat++;
            }else if(numberOfAnimal == 6){
                //mouce
                cell.getMouse().add(new Mouse());
                Mouse.newMouse++;
            }else if(numberOfAnimal == 7){
                //rabbit
                cell.getRabbit().add(new Rabbit());
                Rabbit.newRabbit++;
            }else if(numberOfAnimal == 8){
                //deer
                cell.getDeer().add(new Deer());
                Deer.newDeer++;
            }else if(numberOfAnimal == 9){
                //horse
                cell.getHorse().add(new Horse());
                Horse.newHorse++;
            }else if(numberOfAnimal == 10){
                //eagle
                cell.getEagle().add(new Eagle());
                Eagle.newEagle++;
            }else if(numberOfAnimal == 11){
                //bear
                cell.getBear().add(new Bear());
                Bear.newBear++;
            }else if(numberOfAnimal == 12){
                //fox
                cell.getFox().add(new Fox());
                Fox.newFox++;
            }else if(numberOfAnimal == 13){
                //boa
                cell.getBoa().add(new Boa());
                Boa.newBoa++;
            }else if(numberOfAnimal == 14){
                //wolf
                cell.getWolf().add(new Wolf());
                Wolf.newWolf++;
            }

        }
    }

}
