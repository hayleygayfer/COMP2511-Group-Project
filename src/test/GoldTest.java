package test;

import java.util.List;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import unsw.loopmania.Character;
import unsw.loopmania.Gold;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;

public class GoldTest {

    @Test
    public void testConstructor() {
        Gold coin1 = new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(coin1 instanceof Gold);
        assertTrue(coin1 instanceof StaticEntity);
        assertTrue(coin1 instanceof Entity);
    }

    @Test
    public void testSpawnFrequency() {
        LoopManiaWorld world = TestHelper.createWorld();
        Character character = TestHelper.createCharacter();
        world.setCharacter(character);
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
        LoopManiaWorld world = TestHelper.createWorld();
        Character character = TestHelper.createCharacter();
        world.setCharacter(character);
        List<Pair<Integer, Integer>> path = TestHelper.createPath();

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
    public void testCharacterMovesOverGold() {
        LoopManiaWorld world = TestHelper.createWorld();
        Character character = TestHelper.createCharacter();
        world.setCharacter(character);

        Gold gold = new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1)); 
        character.attach(gold);

        int initialTotal = character.getGold().get(); 

        world.runTickMoves();

        assertEquals(initialTotal + 1, character.getGold().get());
    }

    @Test
    public void testCharacterDoesntReachGold() {
        LoopManiaWorld world = TestHelper.createWorld();
        Character character = TestHelper.createCharacter();
        world.setCharacter(character);

        Gold gold = new Gold(new SimpleIntegerProperty(0), new SimpleIntegerProperty(2)); 
        character.attach(gold);

        int initialTotal = character.getGold().get(); 

        world.runTickMoves();

        assertEquals(initialTotal, character.getGold().get());
    }
}
