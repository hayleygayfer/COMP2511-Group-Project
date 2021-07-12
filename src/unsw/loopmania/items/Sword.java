package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends EquippableItem {
    private int damageBonus;

    // TODO write sword
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        damageBonus = 10;
    }    

    /**
     * Given a character's base damage, returns the modified
     * damage based on the properties of this item
     */
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        return baseDamage + damageBonus;
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
