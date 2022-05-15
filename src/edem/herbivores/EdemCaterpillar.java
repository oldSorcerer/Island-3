package edem.herbivores;

import entity.Cell;
import entity.herbivores.Caterpillar;

import java.util.concurrent.ThreadLocalRandom;

public class EdemCaterpillar implements Runnable{
    Cell[][] cells;

    public EdemCaterpillar(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getCaterpillar().add(new Caterpillar());
                    Caterpillar.newGaterpillar++;
                }

            }
        }
    }
}
