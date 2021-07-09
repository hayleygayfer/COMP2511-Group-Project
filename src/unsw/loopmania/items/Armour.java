package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import javafx.beans.property.SimpleIntegerProperty;

public class Armour extends EquippableItem {
    
    // TODO write armour
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Halves the enemy's prospective base damage,
     */
    @Override
    public int getModifiedEnemyDamge(int baseDamage) {
        // TODO Auto-generated method stub
        return super.getModifiedEnemyDamge(baseDamage);
    }
}
