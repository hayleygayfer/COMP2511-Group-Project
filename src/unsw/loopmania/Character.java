package unsw.loopmania;

import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements CharacterPositionSubject {
    // inventory > list of items
    private List<Item> inventory;
    private List<Item> equippedItems;

    // damage strategy (what weapon is equipped)
    private DamageStrategy damageStrategy;
    private SimpleIntegerProperty health;
    private SimpleIntegerProperty baseDamage;

    // defence strategy
    private SimpleIntegerProperty baseDefence;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
    }

    public int getHealth() {
        return health.get();
    }

    public SimpleIntegerProperty health() {
        return health;
    }

    public int getDamage() {
        for (Item item : equippedItems) {

        }
        return damageStrategy.getModifiedDamage(baseDamage).get();
    }
    
    /**
     * Adds an item to the character's inventory
     */
    public void addItemToInventory(Item item) {

    }

    /**
     * Returns the items in the inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Equips item from the inventory
     */
    public void equipItem(Item item) {

    }

    /**
     * Unequips item - moves it to inventory
     */
    public void unequipItem(Item item) {

    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    /**
     * 
     */
    public void removeItemFromInventory(Item item) {

    }

}
