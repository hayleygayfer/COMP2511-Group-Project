package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PurchaseItem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Staff extends EquippableItem implements PurchaseItem {
    private int baseDamage;

    // TODO write staff
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        baseDamage = 2;
    }

    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        return this.baseDamage;
    }

    /**
     * Applies damage to target
     * Also has a chance of applying trance
     */
    @Override
    public void attack(MovingEntity target, int damage) {
        // TODO Auto-generated method stub
        super.attack(target, damage);
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Staff");
    }
}
