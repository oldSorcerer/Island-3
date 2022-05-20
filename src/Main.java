import edem.Edem;

import edem.EdemPlants;
import entity.Cell;
import entity.Plant;
import entity.herbivores.Caterpillar;
import entity.herbivores.Duck;
import world.Life;
import world.LifeCycle;
import world.LifeStep;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        //begin initial block-------------------------------------------------------------------------------------------
        Cell[][] cells = new Cell[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell();
            }
        }
        Edem.runEdem(cells);
        //end initial block---------------------------------------------------------------------------------------------
        //begin world life----------------------------------------------------------------------------------------------



        int step = 0;
        while(step <8){
            Thread.sleep(1000);
            Life.runLife(cells);
            step++;
        }

        Edem.shutdown();

//        System.out.println("Plants:" + Plant.newPlants+"/"+Plant.deathPlants+
//                            "   Caterpillars:"+Caterpillar.newGaterpillar+"/"+Caterpillar.deathGaterpillar+
//                            "   Duck:" + Duck.newDuck+"/"+Duck.deathDuck);



    }
}
