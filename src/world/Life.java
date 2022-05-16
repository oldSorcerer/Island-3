package world;

import entity.Cell;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Life {

    static ScheduledExecutorService lifeCycle = Executors.newScheduledThreadPool(3);

    public static void runLife(Cell[][] cells){
//        lifeCycle.scheduleAtFixedRate(new LifeStep(),0,900,TimeUnit.MILLISECONDS);
//        lifeCycle.scheduleAtFixedRate(new LifeCycle(cells),0,1000, TimeUnit.MILLISECONDS);

    }

    public static void shutdown(){
        lifeCycle.shutdown();
    }
}
