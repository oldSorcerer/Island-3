import edem.Edem;

import entity.Cell;
import world.Life;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        //begin initial block-------------------------------------------------------------------------------------------
        Cell[][] cells = new Cell[5][5];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }
        Edem.runEdem(cells);
        //end initial block---------------------------------------------------------------------------------------------
        //begin world life----------------------------------------------------------------------------------------------



        int step = 0;
        while(step <5){
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
