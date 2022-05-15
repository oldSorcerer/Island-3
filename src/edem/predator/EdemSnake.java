package edem.predator;

import entity.Cell;
import entity.predator.Snake;

public class EdemSnake implements Runnable{
    Cell[][] cells;

    public EdemSnake(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 10; k++) {
                    cells[i][j].getSnake().add(new Snake());
                    Snake.newSnake++;
                }

            }
        }
    }
}
