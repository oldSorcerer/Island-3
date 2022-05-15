package entity.predator;

import entity.Animal;
import entity.verb.Eat;
import entity.verb.Move;
import entity.verb.Reproduce;

public abstract class Predator extends Animal implements Eat, Reproduce, Move {
}
