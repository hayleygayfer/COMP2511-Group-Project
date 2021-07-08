package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class EquippableItem extends Item implements EquipItemStrategy {
    public EquippableItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
