package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class EquippableItem extends Item {


    public EquippableItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    /**
     * Checks whether an item can be equipped
     */
    public boolean isEquippable(List<Item> equippedItems) {
        return true;
    }

    /**
     * Given a character's base damage, returns the modified
     * damage based on the properties of this item
     */
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        return baseDamage;
    }

    /**
     * Given the enemy's base damage, returns the
     * modified damage based on the properties of
     * this item
     */
    public int getModifiedEnemyDamage(int baseDamage) {
        return baseDamage;
    }


    /**
     * Given a critical hit chance, returns the modified
     * critical hit chance based on the properties of this item
     */
    public double getModifiedCriticalChance(double baseCriticalChance) {
        return baseCriticalChance;
    }

    /**
     * Given a target enemy, applies the attack to that enemy
     */
    public void attack(MovingEntity target, int damage) {
        
    }

}
