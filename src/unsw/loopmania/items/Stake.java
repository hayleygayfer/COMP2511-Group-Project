package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;
public class Stake extends EquippableItem {
    // TODO write stake
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        // TODO Auto-generated method stub
        return super.getModifiedDamage(target, baseDamage);
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
