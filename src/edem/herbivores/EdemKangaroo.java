package edem.herbivores;

import entity.Cell;
import entity.herbivores.Caterpillar;
import entity.herbivores.Kangaroo;

public class EdemKangaroo implements Runnable{
    Cell[][] cells;

    public EdemKangaroo(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getKangaroo().add(new Kangaroo());
                    Kangaroo.newKangaroo++;
                }

            }
        }
    }
}
