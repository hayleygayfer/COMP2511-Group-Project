package test.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import test.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Character;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;

public class VillageBuildingTest {
    @Test
    public void testConstructor() {
        VillageBuilding village = new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));

        assertTrue(village instanceof VillageBuilding);
        assertTrue(village instanceof Building);
        assertTrue(village instanceof StaticEntity);
        assertTrue(village instanceof Entity);

        assertEquals(1, village.getX());
        assertEquals(0, village.getY());
    }
    
    @Test
    public void testEncounter() {
        List<Pair<Integer, Integer>> path = TestHelper.createPath();
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        VillageBuilding village = new VillageBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        int initialHealth = character.getHealth();

        character.moveDownPath();
        // character is now on same tile as village
        village.encounter(character);
        
        assertEquals(initialHealth + 10, character.getHealth());
    }

    @Test
    public void testNoEncounter() {
        List<Pair<Integer, Integer>> path = TestHelper.createPath();
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        VillageBuilding village = new VillageBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        int initialHealth = character.getHealth();

        // character is not on same tile as village
        village.encounter(character);
        
        assertEquals(initialHealth, character.getHealth());
    }
}
