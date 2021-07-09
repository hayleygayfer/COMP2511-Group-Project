package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;
public class Staff extends EquippableItem {
    // TODO write staff
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
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
}
