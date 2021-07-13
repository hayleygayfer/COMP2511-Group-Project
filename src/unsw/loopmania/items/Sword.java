package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends EquippableItem {
    // TODO write sword
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    

    /**
     * Applies damage to the target
     */
    @Override
    public void attack(MovingEntity target, int damage) {
        // TODO Auto-generated method stub
        super.attack(target, damage);
    }
}
