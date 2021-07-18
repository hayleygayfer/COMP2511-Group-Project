package test.buildings;


import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.buildings.CampfireBuilding;

public class CampfireBuildingTest {
    @Test
    public void testSpawnEnemy(){

      CampfireBuilding building = new CampfireBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
      
      // TODO: Test effect on character.

    }
}
