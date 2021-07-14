package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.Character;
import unsw.loopmania.PurchaseItem;
import unsw.loopmania.UsableItem;

public class HealthPotion extends UsableItem implements PurchaseItem {
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

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("HealthPotion");
    }
}
