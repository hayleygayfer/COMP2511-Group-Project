package test;

import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;

public class TestHelper {
   /**
     * Creates the path for testing
     * just a 5x5 square loop
     */
    public static List<Pair<Integer, Integer>> createPath() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

        // add top horizontal
        for (int i = 0; i < 5; i++) {
            orderedPath.add(Pair.with(0, i));
        }
        // add right side down
        for (int i = 1; i < 5; i++) {
            orderedPath.add(Pair.with(i, 4));
        }
        // add bottom horizontal
        for (int i = 4; i >= 0; i--) {
            orderedPath.add(Pair.with(4, i));
        }
        // add left side up
        for (int i = 4; i >= 0; i--) {
            orderedPath.add(Pair.with(i, 0));
        }

        return orderedPath;
    }

    public static Character createCharacter() {
        return new Character(new PathPosition(0, createPath()));
    }

    public static LoopManiaWorld createWorld() {
        LoopManiaWorld world = new LoopManiaWorld(6, 6, createPath());
        world.setCharacter(createCharacter());
        return world;
    }
}
