package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;

import org.hamcrest.core.IsInstanceOf;
import org.javatuples.Pair;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements CharacterPositionSubject {
    // inventory > list of items
    private List<Item> inventory;
    private List<EquippableItem> equippedItems;

    // Position Observers
    private List<CharacterPositionObserver> observers = new ArrayList<CharacterPositionObserver>();

    // Base & Battle Damage & Health
    private SimpleIntegerProperty baseDamage;
    private SimpleIntegerProperty baseHealth;
    private SimpleIntegerProperty battleDamage;
    private SimpleIntegerProperty battleHealth;


    // Initial position
    private Pair<Integer, Integer> initialPosition;
    // gold
    private SimpleIntegerProperty gold;
    // XP
    private SimpleIntegerProperty xp;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
        this.initialPosition = new Pair<Integer, Integer>(position.getX().getValue(), position.getY().getValue());
        this.gold = new SimpleIntegerProperty(0);
        this.xp = new SimpleIntegerProperty(0);
        this.baseHealth = new SimpleIntegerProperty(50);
        this.battleHealth = new SimpleIntegerProperty(50);
        this.baseDamage = new SimpleIntegerProperty(5);
        this.battleDamage = new SimpleIntegerProperty(5);
        inventory = new ArrayList<Item>();
        equippedItems = new ArrayList<EquippableItem>();
    }

    public SimpleIntegerProperty getXpProperty() {
        return xp;
    }

    /**
     * Base Health Getter
     * @return baseHealth int
     */
    public int getBaseHealth() {
        return baseHealth.get();
    }

    /**
     * Base Health Property Getter
     * @return baseHealth int
     */
    public SimpleIntegerProperty getBaseHealthProperty() {
        return baseHealth;
    }

    /**
     * Battle Health Getter
     * @param damage
     */
    public int getBattleHealth() {
        return baseHealth.get();
    }

    /**
     * Battle Health Setter
     * @param damage
     */
    public void setBattleHealth(int health) {
        battleHealth.set(health);
    }

    /**
     * Base Health Setter
     * @param damage
     */
    public void setBaseHealth(int health) {
        baseHealth.set(health);
    }

    /**
     * Resets health after a battle
     */
    public void resetHealth() {
        if (getBaseHealth() > getBattleHealth()) {
            setBaseHealth(getBattleHealth());
        } else {
            setBattleHealth(getBaseHealth());
        }
    }

    /**
     * Base Damage Getter
     * @return
     */
    public int getBaseDamage() {
        return baseDamage.get();
    }

    /**
     * Battle Damage Getter
     * @param damage
     */
    public int getBattleDamage() {
        return baseDamage.get();
    }

    /**
     * Battle Damage Setter
     * @param damage
     */
    public void setBattleDamage(int damage) {
        baseDamage.set(damage);
    }

    /**
     * Returns whether or not the character is alive
     */
    public boolean isAlive() {
        return getBattleHealth() > 0;
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
        inventory.remove(item);
        equippedItems.add(item);
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

    public List<EquippableItem> getEquippedItems() {
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

    /**
     * Attach an observer to the character
     * @param observer The observer to attach.
     */
    public void attach(CharacterPositionObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Detach an observer to the character
     * @param observer The observer to attach.
     */
    public void detach(CharacterPositionObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Updates observers of character position
     */
    public void updateObservers() {
        for (CharacterPositionObserver observer : observers) {
            observer.encounter(this);
        }
    }

    /**
     * Determines whether or not the character is at the heros castle.
     * @return boolean indicating whether or not the character is at the castle.
     */
    public boolean isAtHerosCastle() {
        return (getPosition().getPositionPair().equals(initialPosition));
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

    /**
     * Attack
     * @param enemy
     */
    public void attack(BasicEnemy enemy) {
        for (EquippableItem item : getEquippedItems()) {
            if (item instanceof CustomAttackStrategy) {
                CustomAttackStrategy customAttackStrategy = (CustomAttackStrategy) item;
                customAttackStrategy.attack(enemy);
                return;
            }
        }
        System.out.println("BATTLE DAMAGE = " + getBattleDamage());
        enemy.setHealth(enemy.getHealth() - getBattleDamage());
    }
}
