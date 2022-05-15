package edem.herbivores;

import entity.Cell;
import entity.herbivores.Horse;

public class EdemHorse implements Runnable{
    Cell[][] cells;

    public EdemHorse(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getHorse().add(new Horse());
                    Horse.newHorse++;
                }

            }
        }
    }
}
