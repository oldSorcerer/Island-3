package entity.herbivores;

import entity.Animal;
import entity.verb.Eat;
import entity.verb.Move;
import entity.verb.Reproduce;

public abstract class Herbivores extends Animal implements Eat, Reproduce, Move {
}
