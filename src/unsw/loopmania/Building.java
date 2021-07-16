package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class Building extends StaticEntity {
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public List<Pair<Integer, Integer>> getAdjacentSquares() {
        List<Pair<Integer, Integer>> adjacentSquares = new ArrayList<>();

        // having out of bounds squares doesn't matter
        adjacentSquares.add(new Pair<Integer, Integer>(getX() - 1, getY() - 1));
        adjacentSquares.add(new Pair<Integer, Integer>(getX() - 1, getY()));
        adjacentSquares.add(new Pair<Integer, Integer>(getX() - 1, getY() + 1));
        adjacentSquares.add(new Pair<Integer, Integer>(getX(), getY() - 1));
        adjacentSquares.add(new Pair<Integer, Integer>(getX(), getY() + 1));
        adjacentSquares.add(new Pair<Integer, Integer>(getX() + 1, getY() - 1));
        adjacentSquares.add(new Pair<Integer, Integer>(getX() + 1, getY()));
        adjacentSquares.add(new Pair<Integer, Integer>(getX() + 1, getY() + 1));

        return adjacentSquares;
    }
}
