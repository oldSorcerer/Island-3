package entity.verb;

import entity.Animal;
import entity.Cell;

public interface Move {
    Animal move(Cell[][] cells, int i, int j);
}
