package world;

import entity.Animal;
import entity.Cell;
import entity.herbivores.Caterpillar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Life {

    public static void runLife(Cell[][] cells){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
                for (int k = 0; k < caterpillars.size(); k++) {
                    Caterpillar caterpillar = caterpillars.get(k);
                }
            }
        }
    }


}
