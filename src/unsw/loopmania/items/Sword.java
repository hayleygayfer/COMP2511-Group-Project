package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.PurchaseItem;
import unsw.loopmania.Character;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends EquippableItem implements PurchaseItem {
    private int damage;

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        damage = 10;
    }    

    @Override
    public void affect(Character character) {
        character.setBattleDamage(character.getBattleDamage() + damage);
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(10);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Sword");
    }
}
