package edem.predator;

import entity.Cell;
import entity.predator.Fox;

public class EdemFox implements Runnable{
    Cell[][] cells;

    public EdemFox(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getFox().add(new Fox());
                    Fox.newFox++;
                }

            }
        }
    }
}
