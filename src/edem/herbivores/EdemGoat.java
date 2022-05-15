package edem.herbivores;

import entity.Cell;
import entity.herbivores.Goat;

public class EdemGoat implements Runnable{
    Cell[][] cells;

    public EdemGoat(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getGoat().add(new Goat());
                    Goat.newGoat++;
                }

            }
        }
    }
}
