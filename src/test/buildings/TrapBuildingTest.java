package test.buildings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Character;

public class TrapBuildingTest {
    @Test
    public void testSpawnEnemy(){

      TrapBuilding building = new TrapBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
      
      // TODO: Test effect on enemy position

    }
}

