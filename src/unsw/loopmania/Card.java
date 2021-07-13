package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class Card extends StaticEntity {

    /** 
     * @param x
     * @param y
     * Determines whether or not, a location is valid, for a building.
     * @return boolean
     */
    abstract public boolean isValidPosition(SimpleIntegerProperty x, SimpleIntegerProperty y);

    /** 
     * Generates a building.
     */
    abstract public Building generateBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);

    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
