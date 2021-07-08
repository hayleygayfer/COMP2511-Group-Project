package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class EquippableItem extends Item {


    public EquippableItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    /**
     * Checks whether an item can be equipped
     */
    public boolean isEquippable(List<Item> equippedItems) {
        return true;
    }
}
