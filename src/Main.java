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
        Cell[][] cells = new Cell[10][10];
        //заполняем матрицу ячейками
        initCells(cells);
        Edem.runEdem(cells);

        //end initial block---------------------------------------------------------------------------------------------
        //begin world life----------------------------------------------------------------------------------------------

        Life.runLife(cells);
//        for (int i = 0; i < cells.length; i++) {
//            for (int j = 0; j < cells[0].length; j++) {
//                for (int k = 0; k < 10; k++) {
//                    cells[i][j].getDuck().add(new Duck());
//                    Duck.newDuck++;
//                }
//            }
//        }
        Thread.sleep(1000);
        int step=1;
        while(step <8){

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
//

                System.out.println("["+(i+1)+", " +(j+1)+"] - "+ cells[i][j]);
                }
                System.out.println(step+"--------------------------------------------------------------------------------------------------------------------------------");
            }
            Thread.sleep(1000);
            step++;
        }

        Edem.shutdown();

        System.out.println("Caterpillars:"+Caterpillar.newGaterpillar+"/"+Caterpillar.deathGaterpillar+
                            "    Duck:" + Duck.newDuck+"/"+Duck.deathDuck);



    }

    private static void initCells(Cell[][] cells){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
