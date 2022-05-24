package edem;

import entity.Cell;
import entity.Plant;

import java.util.concurrent.ThreadLocalRandom;

public class EdemPlants implements Runnable{
    Cell[][] cells;

    public EdemPlants(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 50; k++) {
                    if(Cell.MAX_PLANT > cells[i][j].getSynchronizedPlant().size()){
                        cells[i][j].getSynchronizedPlant().add(new Plant());
                        Plant.newPlants++;
                    }
                }
            }
        }
    }
}
