package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import java.io.File;
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
    private SimpleIntegerProperty currentHealth;
    private SimpleIntegerProperty modifiedHealth;
    private SimpleIntegerProperty modifiedDamage;

    // Initial position
    private Pair<Integer, Integer> initialPosition;
    // gold
    private SimpleIntegerProperty gold;
    // XP
    private SimpleIntegerProperty xp;

    // allied soldiers
    private List<AlliedSoldier> alliedSoldiers;


    public Character(PathPosition position) {
        super(position);
        this.initialPosition = new Pair<Integer, Integer>(position.getX().getValue(), position.getY().getValue());
        this.gold = new SimpleIntegerProperty(100);
        this.xp = new SimpleIntegerProperty(0);
        this.baseHealth = new SimpleIntegerProperty(50);
        this.inventory = new ArrayList<Item>();
        this.equippedItems = new ArrayList<EquippableItem>();
        this.alliedSoldiers = new ArrayList<AlliedSoldier>();
        this.modifiedHealth = new SimpleIntegerProperty(50);
        this.currentHealth = new SimpleIntegerProperty(50);
        this.baseDamage = new SimpleIntegerProperty(1);
        this.modifiedDamage = new SimpleIntegerProperty(1);
        this.inventory = new ArrayList<Item>();
        this.equippedItems = new ArrayList<EquippableItem>();
    }

    public Image render() {
        return new Image((new File("src/images/human_new.png")).toURI().toString());
    }

    public SimpleIntegerProperty getXpProperty() {
        return xp;
    }


    /**
     * Modified Health Getter (After Shields + Armour)
     * @return baseHealth int
     */
    public int getModifiedHealth() {
        return modifiedHealth.get();
    }

    /**
     * Increases health by given amount, but without exceeding baseHealth
     * @param increase
     * @post health <= baseHealth
     */
    public void gainHealth(int increase) {
        this.currentHealth.set(Math.min(baseHealth.get(), currentHealth.get() + increase));
    }

    public int getBaseHealth() {
        return baseHealth.get();
    }

    public int getCurrentHealth() {
        return currentHealth.get();
    }

    /**
     * Base Health Property Getter
     * @return baseHealth int
     */
    public SimpleIntegerProperty getBaseHealthProperty() {
        return baseHealth;
    }

    /**
     * Current Health Property Getter
     * @return baseHealth int
     */
    public SimpleIntegerProperty getCurrentHealthProperty() {
        return currentHealth;
    }

    /**
     * Returns whether or not the character is alive
     */
    public boolean isAlive() {
        return getModifiedHealth() > 0;
    }

    public void addXp(int experiencePoints) {
        xp.set(this.xp.get() + experiencePoints);
    }

    /**
     * Battle Health Setter
     * @param damage
     */
    public void setModifiedHealth(int health) {
        modifiedHealth.set(health);
    }

    /**
     * Current Health Setter
     * @param damage
     */
    public void setCurrentHealth(int health) {
        currentHealth.set(health);
    }

    /**
     * Resets health after a battle
     */
    public void resetHealth() {
        if (getModifiedHealth() < getCurrentHealth()) {
            setCurrentHealth(getModifiedHealth());
        } else {
            setModifiedHealth(getCurrentHealth());
        }
        setModifiedDamage(getBaseDamage());
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
    public int getModifiedDamage() {
        return modifiedDamage.get();
    }

    /**
     * Battle Damage Setter
     * @param damage
     */
    public void setModifiedDamage(int damage) {
        modifiedDamage.set(damage);
    }

    /**
     * Adds an item to the character's inventory
     */
    public void addItemToInventory(Item item) {
        inventory.add(item);
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
    
    public SimpleIntegerProperty getGoldProperty() {
        return gold;
    }

    public int getGold() {
        return gold.get();
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
     * Adds an allied soldiier to the character
     * @param newSoldier
     * @pre newSoldier != NULL and is not currently in the list
     */
    public void addSoldier(AlliedSoldier newSoldier) {
        alliedSoldiers.add(newSoldier);
    }

    public List<AlliedSoldier> getAlliedSoldiers() {
        return alliedSoldiers;
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
        enemy.setHealth(enemy.getHealth() - getModifiedDamage());
    }
}
