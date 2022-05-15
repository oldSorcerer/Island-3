package edem.herbivores;

import entity.Cell;
import entity.herbivores.Rabbit;
import entity.herbivores.Sheep;

public class EdemSheep implements Runnable{
    Cell[][] cells;

    public EdemSheep(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getSheep().add(new Sheep());
                    Sheep.newSheep++;
                }

            }
        }
    }
}