package test;

import java.util.List;


import java.util.ArrayList;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import unsw.loopmania.Character;
import unsw.loopmania.Gold;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;

public class GoldTest {
    /**
     * Creates the path for testing
     * just a 5x5 square loop
     */
    public List<Pair<Integer, Integer>> createPath() {
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

    public Character createCharacter() {
        return new Character(new PathPosition(0, createPath()));
    }

    public LoopManiaWorld createWorld() {
        LoopManiaWorld world = new LoopManiaWorld(6, 6, createPath());

        return world;
    }

    @Test
    public void testConstructor() {
        Gold coin1 = new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(coin1 instanceof Gold);
        assertTrue(coin1 instanceof StaticEntity);
        assertTrue(coin1 instanceof Entity);
    }

    @Test
    public void testSpawnFrequency() {
        LoopManiaWorld world = createWorld();
        // spawns approximately 10% of the time
        int spawns = 0;
        for (int i = 0; i < 1000; i++) {
            List<Gold> spawnedGold = world.possiblySpawnGold();
            if (spawnedGold.size() > 0) {
                spawns++;
            }
        }

        assertTrue(spawns > 90 && spawns < 110);
    }

    @Test
    public void testSpawnPositions() {
        LoopManiaWorld world = createWorld();
        List<Pair<Integer, Integer>> path = createPath();

        // always spawns on a path tile
        for (int i = 0; i < 100; i++) {
            List<Gold> spawnedGold = world.possiblySpawnGold();
            if (spawnedGold.size() > 0) {
                for (Gold gold : spawnedGold) {
                    Pair<Integer, Integer> goldPosition = new Pair<>(gold.getX(), gold.getY());
                    assertTrue(path.contains(goldPosition));
                }
            }
        }
    }

    @Test
    public void testCollectGold() {

    }

}
