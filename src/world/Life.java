package world;

import com.sun.tools.javac.Main;
import entity.Animal;
import entity.Cell;
import entity.Plant;
import entity.herbivores.*;

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
                Buffalo.life(cells,i,j);
                Boar.life(cells,i,j);
                Sheep.life(cells,i,j);
                Goat.life(cells,i,j);
                Mouse.life(cells,i,j);
                Rabbit.life(cells,i,j);
                System.out.println("["+(i+1)+", " +(j+1)+"] - "+ cells[i][j]);
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Plants:" + Plant.newPlants+"/"+Plant.deathPlants+
                    "   Caterpillars:"+Caterpillar.newGaterpillar+"/"+Caterpillar.deathGaterpillar +
                    "   Duck:" + Duck.newDuck+"/"+Duck.deathDuck+
                    "   Buffalo:" + Buffalo.newBuffalo + "/" + Buffalo.deathBuffalo +
                    "   Boar:" + Boar.newBoar + "/" + Boar.deathBoar +
                    "   Sheep:" + Sheep.newSheep + "/" + Sheep.deathSheep +
                    "   Goat:" + Goat.newGoat + "/" + Goat.deathGoat +
                    "   Mouse:" + Mouse.newMouse + "/" + Mouse.deathMouse +
                    "   Rabbit:" + Rabbit.newRabbit + "/" + Rabbit.deathRabbit);
        }
    }
}



