package test.buildings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.buildings.VampireCastleBuilding;
import unsw.loopmania.enemies.Vampire;

public class VampireCastleBuildingTest {
    @Test
    public void testSpawnEnemy(){

      VampireCastleBuilding building = new VampireCastleBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
      BasicEnemy generatedEnemy = building.spawnEnemy();
      
      // Tests that generate enemy generates a BasicEnemy of type Vampire.
      assertTrue(generatedEnemy instanceof Vampire);

      // TODO: Test for correct placement.

    }
}
