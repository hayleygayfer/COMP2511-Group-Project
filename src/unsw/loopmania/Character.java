package unsw.loopmania;

import java.util.List;
import java.util.ArrayList;
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

    // gold
    private SimpleIntegerProperty gold;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        this.gold = new SimpleIntegerProperty(0);
        inventory = new ArrayList<Item>();
        equippedItems = new ArrayList<Item>();
    }

    public int getHealth() {
        return health.get();
    }

    /**
     * Deducts health
     */
    public void loseHealth(double damage) {
        this.health.subtract(damage);
    }

    /**
     * Returns whether or not the character is alive
     */
    public boolean isAlive() {
        return getHealth() > 0;
    }

    public SimpleIntegerProperty health() {
        return health;
    }

    public int getDamage() {
        // will have to make sure that attack item
        // damage is applied first
        for (Item item : equippedItems) {

        }
        return damageStrategy.getModifiedDamage(baseDamage.get());
    }

    /**
     * Applies an attack on the enemy, given the amount
     * of damage you can do
     */
    public void attack(BasicEnemy enemy, int damage) {
        // just apply the attack from every equipped item
    }
    
    /**
     * Adds an item to the character's inventory
     */
    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void addItemsToInventory(List<Item> items) {
        inventory.addAll(items);
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
    public void equipItem(EquippableItem item) {
        
    }

    /**
     * Uses the item and applies its effect
     */
    public void useItem(UsableItem item) {

    }

    /**
     * Unequips item - moves it to inventory
     */
    public void unequipItem(EquippableItem item) {
        equippedItems.remove(item);
        inventory.add(item);
    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    /**
     * 
     */
    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    public void removeItemByIndex(int index) {
        inventory.remove(index);
    }

    public SimpleIntegerProperty getGold() {
        return gold;
    }

    /**
     * Deducts the cost of something from the total amount of gold this character has
     * @param cost
     */
    public void deductGold(int cost) {
        this.gold.set(this.gold.get() - cost);
    }

    /**
     * Adds the amount of gold to the total amount of gold this character has
     * @param amount
     */
    public void addGold(int amount) {
        this.gold.set(this.gold.get() + amount);
    }

}
