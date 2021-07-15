package test.buildings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.buildings.ZombiePitBuilding;
import unsw.loopmania.enemies.Zombie;

public class ZombiePitBuildingTest {
    @Test
    public void testSpawnEnemy(){

      ZombiePitBuilding building = new ZombiePitBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
      BasicEnemy generatedEnemy = building.spawnEnemy();
      
      // Tests that generate enemy generates a BasicEnemy of type Zombie.
      assertTrue(generatedEnemy instanceof Zombie);

    }
}
