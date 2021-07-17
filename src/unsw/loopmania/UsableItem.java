package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class UsableItem extends Item {
    public UsableItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Applies effect of the item to the character
     */
    @Override
    public void applyEffect(Character character) {
        
    }
}
