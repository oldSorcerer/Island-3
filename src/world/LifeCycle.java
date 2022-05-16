package world;

import entity.Cell;
import entity.Plant;
import entity.herbivores.Caterpillar;
import entity.herbivores.Duck;

import java.util.ArrayList;
import java.util.List;

public class LifeCycle implements Runnable{
    Cell[][] cells;


    public LifeCycle(Cell[][] cells) {
        this.cells = cells;
    }
    int eatC = 0;
    int hungryC = 0;
    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                //сперва начинают ходить гусеницы
                //Caterpillar.life(cells,i,j);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // Duck.life(cells,i,j);
            }
        }
    }
}
