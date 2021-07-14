package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;

import org.javatuples.Pair;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements CharacterPositionSubject {
    // inventory > list of items
    private List<Item> inventory;
    private List<Item> equippedItems;

    // Position Observers
    private List<CharacterPositionObserver> observers = new ArrayList<CharacterPositionObserver>();

    // damage strategy (what weapon is equipped)
    private DamageStrategy damageStrategy;
    private SimpleIntegerProperty health;
    private SimpleIntegerProperty baseDamage;

    // defence strategy
    private SimpleIntegerProperty baseDefence;

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
        this.health = new SimpleIntegerProperty(50);
    }

    public SimpleIntegerProperty getXpProperty() {
        return xp;
    }

    public int getHealth() {
        return health.get();
    }

    public SimpleIntegerProperty getHealthProperty() {
        return health;
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

    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    /**
     * 
     */
    public void removeItemFromInventory(Item item) {

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

}
