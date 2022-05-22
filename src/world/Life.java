package world;

import entity.Animal;
import entity.Cell;
import entity.Plant;
import entity.herbivores.Caterpillar;
import entity.herbivores.Duck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Life {

    public static void runLife(Cell[][] cells){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Caterpillar.life(cells,i,j);
                Duck.life(cells,i,j);
                System.out.println("["+(i+1)+", " +(j+1)+"] - "+ cells[i][j]);
            }
            System.out.println(LifeStep.lifeStep +"--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Plants:" + Plant.newPlants+"/"+Plant.deathPlants+
                    "   Caterpillars:"+Caterpillar.newGaterpillar+"/"+Caterpillar.deathGaterpillar+
                    "   Duck:" + Duck.newDuck+"/"+Duck.deathDuck);
        }
    }
}



