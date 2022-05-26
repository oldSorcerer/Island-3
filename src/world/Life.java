package world;

import entity.Cell;
import entity.Plant;
import entity.herbivores.*;
import entity.predator.*;

public class Life {

    public static void runLife(Cell[][] cells, int step){
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
                Deer.life(cells,i,j);
                Horse.life(cells,i,j);
                Eagle.life(cells,i,j);
                Bear.life(cells,i,j);
                Fox.life(cells,i,j);
                Boa.life(cells,i,j);
                Wolf.life(cells,i,j);
                System.out.println("["+(i+1)+", " +(j+1)+"] - "+ cells[i][j]);
            }
            System.out.println("STEP: " + step);
            System.out.println(
                    "   Plants:" + Plant.newPlants+"/"+Plant.deathPlants+
                    "   Caterpillars:"+Caterpillar.newGaterpillar+"/"+Caterpillar.deathGaterpillar +
                    "   Duck:" + Duck.newDuck+"/"+Duck.deathDuck+
                    "   Buffalo:" + Buffalo.newBuffalo + "/" + Buffalo.deathBuffalo +
                    "   Boar:" + Boar.newBoar + "/" + Boar.deathBoar +
                    "   Sheep:" + Sheep.newSheep + "/" + Sheep.deathSheep +
                    "   Goat:" + Goat.newGoat + "/" + Goat.deathGoat +
                    "   Mouse:" + Mouse.newMouse + "/" + Mouse.deathMouse + "\n" +
                    "   Rabbit:" + Rabbit.newRabbit + "/" + Rabbit.deathRabbit +
                    "   Deer:" + Deer.newDeer + "/" + Deer.deathDeer +
                    "   Horse:" + Horse.newHorse + "/" + Horse.deathHorse +
                    "   Eagle:" + Eagle.newEagle + "/" + Eagle.deathEagle +
                    "   Bear:" +Bear.newBear + "/" + Bear.deathBear +
                    "   Fox:" + Fox.newFox + "/" + Fox.deathFox +
                    "   Boa:" + Boa.newBoa + "/" + Boa.deathBoa +
                    "   Wolf:" + Wolf.newWolf +"/" + Wolf.deathWolf);
        }
    }
}



