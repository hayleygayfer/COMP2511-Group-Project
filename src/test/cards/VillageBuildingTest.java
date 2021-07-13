package test.cards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.cards.TrapCard;
import unsw.loopmania.cards.VillageCard;

public class VillageBuildingTest {
    @Test
    public void testIsValidPosition(){
        VillageCard card = new VillageCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        // Invalid Position
        assertEquals(card.isValidPosition(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)), false);
        // Valid Position
        assertEquals(card.isValidPosition(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)), true);
        // TODO: Add test for valid position, that already has a building on it.
    }

    @Test
    public void testGenerateBuilding(){
        VillageCard card = new VillageCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        VillageBuilding building = new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));

        // Tests that the building is generated at the given coordinates.
        assertEquals(card.generateBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)), building);
    }
    
}
