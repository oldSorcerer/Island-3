package edem.predator;

import entity.Cell;
import entity.predator.Eagle;

public class EdemEagle implements Runnable{
    Cell[][] cells;

    public EdemEagle(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getEagle().add(new Eagle());
                    Eagle.newEagle++;
                }

            }
        }
    }
}
