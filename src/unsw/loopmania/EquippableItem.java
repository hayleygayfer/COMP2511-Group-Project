package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import java.io.File;

public abstract class EquippableItem extends Item {

    public EquippableItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Checks whether an item can be equipped
     */
    public boolean isEquippable(List<Item> equippedItems) {
        return !equippedItems.contains(this);
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
     * Given a target enemy, applies the affect to that enemy
     * @param enemy The enemy to affect
     */
    public void affect(BasicEnemy enemy) {
        return;
    }

    /**
     * Given a character, applies the affect to the character
     * @param character The character to affect.
     */
    public void affect(Character character) {
        return;
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }
}
