package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.UsableItem;

public class HealthPotion extends UsableItem {
    // TODO write health potion
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Increases health
     */
    @Override
    public void applyEffect(Character character) {
        super.applyEffect(character);
    }
}
