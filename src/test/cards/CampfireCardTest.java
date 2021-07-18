package test.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.beans.property.SimpleIntegerProperty;
import test.TestHelper;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;
import java.util.ArrayList;
import java.util.List;
import unsw.loopmania.LoopManiaWorld;

import unsw.loopmania.cards.CampfireCard;
import unsw.loopmania.buildings.CampfireBuilding;

public class CampfireCardTest {
    
    /**
     * @author Angeni
     * Creates the path for testing
     * just a 5x5 square loop
     */
    public List<Pair<Integer, Integer>> createSquarePath(int size, int start) {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

        // add top horizontal
        for (int i = start; i < size; i++) {
        orderedPath.add(Pair.with(i, start));
        }
        // add right side down
        for (int i = start + 1; i < size; i++) {
        orderedPath.add(Pair.with(size - 1, i));
        }
        // add bottom horizontal
        for (int i = (size-2); i >= start; i--) {
        orderedPath.add(Pair.with(i, size-1));
        }
        // add left side up
        for (int i = (size-2); i > start; i--) {
        orderedPath.add(Pair.with(start, i));
        }
        return orderedPath;
    }

    /**
     * @author Angeni
     * @return LoopManiaWorld object
     */
    public LoopManiaWorld createWorld() {
        return new LoopManiaWorld(6, 6, createSquarePath(6, 0));
    }

    
    @Test
    public void testGenerateBuilding(){
        CampfireCard card = new CampfireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        CampfireBuilding building = new CampfireBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));

        // Tests that the building is generated at the given coordinates.
        assertEquals(card.generateBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1)), building);
    }

    @Test
    public void testValidPosition() {
        List<Pair<Integer, Integer>> adjacentPath = createSquarePath(3, 1); // Valid Positions
        List<Pair<Integer, Integer>> worldPath = TestHelper.createSquarePath(6, 0);

        CampfireCard card = new CampfireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Tests that all non path tiles are valid
        for (Pair<Integer, Integer> position: adjacentPath) {
            assertTrue(card.isValidPosition(new SimpleIntegerProperty(position.getValue0()), new SimpleIntegerProperty(position.getValue1()), worldPath));
        }
    }


    public void testInvalidPosition() {
        CampfireCard card = new CampfireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        List<Pair<Integer, Integer>> worldPath = TestHelper.createSquarePath(6, 0);

        // Tests that all path tiles are invalid
        for (Pair<Integer, Integer> position: worldPath) {
            assertFalse(card.isValidPosition(new SimpleIntegerProperty(position.getValue0()), new SimpleIntegerProperty(position.getValue1()), worldPath));
        }
    }
}
