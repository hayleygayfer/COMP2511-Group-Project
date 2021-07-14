package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PurchaseItem;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends EquippableItem implements PurchaseItem {
    private int baseDamage;

    // TODO write sword
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        baseDamage = 10;
    }    

    /**
     * Given a character's base damage, returns the modified
     * damage based on the properties of this item
     */
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        return this.baseDamage;
    }

    /**
     * Applies damage to the target
     */
    @Override
    public void attack(MovingEntity target, int damage) {
        // TODO Auto-generated method stub
        super.attack(target, damage);
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(10);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Sword");
    }
}
