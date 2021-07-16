package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PurchaseItem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Staff extends EquippableItem implements PurchaseItem, CustomAttackStrategy{
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
    public void attack(BasicEnemy enemy) {
        System.out.println("inflict trance");
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Staff");
    }
}
