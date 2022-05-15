package edem.predator;

import entity.Cell;
import entity.predator.Wolf;

public class EdemWolf implements Runnable{
    Cell[][] cells;

    public EdemWolf(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getWolf().add(new Wolf());
                    Wolf.newWolf++;
                }

            }
        }
    }
}
