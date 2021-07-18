package unsw.loopmania.cards;

import java.util.List;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;
import unsw.loopmania.Building;
import javafx.scene.image.Image;
import java.io.File;

public class CampfireCard extends Card {

    // TODO: Create Campfire card

    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    /**
     * Position is valid if it is a path tile, and not hero's castle
     * @param x coordinate of position
     * @param y coordinate of position
     * @param the current path 
     * @return whether the card can be placed at the given position
     */
    public boolean isValidPosition(SimpleIntegerProperty x, SimpleIntegerProperty y, List<Pair<Integer, Integer>> path) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Generates a Campfire building from this card at the supplied position
     * @param x x coordinate of spawn position
     * @param y y coordinate of spawn position
     * @pre the supplied position is a valid spawn position
     * @return the generated building
     */
    public Building generateBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // TODO Auto-generated method stub
        return null;
    }   

    /**
     * Creates a new image of campfire card
     * @return Image
     */
    public Image render() {
        return new Image((new File("src/images/campfire_card.png")).toURI().toString());
    }
}
