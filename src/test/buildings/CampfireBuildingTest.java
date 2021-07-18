package test.buildings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Character;

public class CampfireBuildingTest {
    @Test
    public void testSpawnEnemy(){

      CampfireBuilding building = new CampfireBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
      
      // TODO: Test effect on character.

    }
}
