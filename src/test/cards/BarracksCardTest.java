package test.cards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.cards.BarracksCard;

public class BarracksCardTest {
    @Test
    public void testIsValidPosition(){
        BarracksCard card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        // Invalid Position
        assertEquals(card.isValidPosition(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)), false);
        // Valid Position
        assertEquals(card.isValidPosition(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)), true);
        // TODO: Add test for valid position, that already has a building on it.
    }
    
    @Test
    public void testGenerateBuilding(){
        BarracksCard card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        BarracksBuilding building = new BarracksBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));

        // Tests that the building is generated at the given coordinates.
        assertEquals(card.generateBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)), building);
    }
    
}
