package test.buildings;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Character;

public class VillageBuildingTest {

    @Test
    public void testSpawnEnemy(){

      VillageBuilding building = new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
      
      // TODO: Test effect on character position

    }
    
}
