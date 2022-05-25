package entity.verb;

import entity.Cell;

import java.util.ArrayList;


public interface Reproduce {
   ArrayList<?> reproduce(Cell[][] cells, int i, int j);
}
