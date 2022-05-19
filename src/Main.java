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


        Life.runLife(cells);

        while(LifeStep.lifeStep <8){
            Thread.sleep(1000);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.println("["+(i+1)+", " +(j+1)+"] - "+ cells[i][j]);
                }
                System.out.println(LifeStep.lifeStep +"--------------------------------------------------------------------------------------------------------------------------------");
            }

        }

        Edem.shutdown();

        System.out.println("Caterpillars:"+Caterpillar.newGaterpillar+"/"+Caterpillar.deathGaterpillar+
                            "    Duck:" + Duck.newDuck+"/"+Duck.deathDuck);



    }
}
