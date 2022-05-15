package edem.herbivores;

import entity.Cell;
import entity.herbivores.Cow;

public class EdemCow implements Runnable{
    Cell[][] cells;

    public EdemCow(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getCow().add(new Cow());
                    Cow.newCow++;
                }

            }
        }
    }
}
