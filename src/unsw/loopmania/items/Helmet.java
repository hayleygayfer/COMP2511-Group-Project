package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;

public class Helmet extends EquippableItem {
    // TODO write helmet
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Decreases your damage
     */
    @Override
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        // TODO Auto-generated method stub
        return super.getModifiedDamage(target, baseDamage);
    }

    /**
     * Decreases the enemy's damage
     */
    @Override
    public int getModifiedEnemyDamge(int baseDamage) {
        // TODO Auto-generated method stub
        return super.getModifiedEnemyDamge(baseDamage);
    }
}
