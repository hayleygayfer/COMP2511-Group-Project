package test.buildings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.buildings.TowerBuilding;
import unsw.loopmania.Character;

public class TowerBuildingTest {
    @Test
    public void testSpawnEnemy(){

      TowerBuilding building = new TowerBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
      
      // TODO: Test effect on character.

    }
}
