package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public class Armour extends EquippableItem {
    
    // TODO write armour
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Can't equip if armour is already equipped
     */
    @Override
    public boolean isEquippable(List<Item> equippedItems) {
        // TODO Auto-generated method stub
        return super.isEquippable(equippedItems);
    }

    /**
     * Halves the enemy's prospective base damage,
     */
    @Override
    public int getModifiedEnemyDamage(int baseDamage) {
        // TODO Auto-generated method stub
        return super.getModifiedEnemyDamage(baseDamage);
    }
}
