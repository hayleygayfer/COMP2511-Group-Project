package test.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.cards.TowerCard;
import unsw.loopmania.cards.TrapCard;


public class TrapCardTest {
    @Test
    public void testIsValidPosition(){
        TrapCard card = new TrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        // Invalid Position
        assertEquals(card.isValidPosition(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)), false);
        // Valid Position
        assertEquals(card.isValidPosition(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)), true);
        // TODO: Add test for valid position, that already has a building on it.
    }

    @Test
    public void testGenerateBuilding(){
        TrapCard card = new TrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        TrapBuilding building = new TrapBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));

        // Tests that the building is generated at the given coordinates.
        assertEquals(card.generateBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)), building);
    }
}
