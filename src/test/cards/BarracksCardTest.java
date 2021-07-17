package test.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;
import java.util.List;

import unsw.loopmania.Building;
import test.TestHelper;
import unsw.loopmania.cards.BarracksCard;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.Card;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;

public class BarracksCardTest {
    @Test
    public void testConstructor() {
        Card card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1)); 

        assertTrue(card instanceof BarracksCard);
        assertTrue(card instanceof Card);
        assertTrue(card instanceof StaticEntity);
        assertTrue(card instanceof Entity);

        assertEquals(0, card.getX());
        assertEquals(1, card.getY());
    }
    
    @Test
    public void testGenerateBuilding(){
        BarracksCard card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        Building building = card.generateBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(1));

        // correct type and coordinates
        assertTrue(building instanceof BarracksBuilding);
        assertEquals(2, building.getX());
        assertEquals(1, building.getY());
    }

    @Test
    public void testInvalidPosition() {
        List<Pair<Integer, Integer>> worldPath = TestHelper.createSquarePath(6, 0);
        List<Pair<Integer, Integer>> adjacentPath = TestHelper.createSquarePath(5, 1); // Invalid Positions

        BarracksCard card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Tests that all non path tiles are invalid
        for (Pair<Integer, Integer> position: adjacentPath) {
            assertFalse(card.isValidPosition(new SimpleIntegerProperty(position.getValue0()), new SimpleIntegerProperty(position.getValue1()), worldPath));
        }
    }

    @Test
    public void testPathPositions() {
        List<Pair<Integer, Integer>> path = TestHelper.createSquarePath(6, 0);
        BarracksCard card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Tests that all path tiles except hero's castle are valid
        for (Pair<Integer, Integer> position: path) {
            if (path.indexOf(position) == 0) {
                assertFalse(card.isValidPosition(new SimpleIntegerProperty(position.getValue0()), new SimpleIntegerProperty(position.getValue1()), path));
            } else {
                assertTrue(card.isValidPosition(new SimpleIntegerProperty(position.getValue0()), new SimpleIntegerProperty(position.getValue1()), path));
            }
        }
    }
}
