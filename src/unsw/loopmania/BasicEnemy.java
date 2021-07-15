package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import java.util.Random;

/**
 * a basic form of enemy in the world
 */
public abstract class BasicEnemy extends MovingEntity implements DamageStrategy, DropLootStrategy, EnemyPositionSubject {
    
    // Enemy stats
    private SimpleIntegerProperty health = new SimpleIntegerProperty();
    private SimpleIntegerProperty baseDamage = new SimpleIntegerProperty();
    private SimpleIntegerProperty battleRadius = new SimpleIntegerProperty();
    private SimpleIntegerProperty supportRadius = new SimpleIntegerProperty();

    private List<EnemyPositionObserver> observers = new ArrayList<EnemyPositionObserver>();
    private List<Pair<Item, Double>> dropChances;

    // Abstract methods
    public abstract Image render();

    // TODO = modify this, and add additional forms of enemy
    public BasicEnemy(PathPosition position) {
        super(position);
    }

    /**
     * move the enemy
     */
    public void move(){
        // TODO = modify this, since this implementation doesn't provide the expected enemy behaviour
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

    // damage

    // This method should be overridden by specific enemies
    public int getModifiedDamage(int baseDamage) {
        return baseDamage;
    }

    public int getDamage() {
        return baseDamage.get();
    }

    public SimpleIntegerProperty damage() {
        return baseDamage;
    }

    public void setDamage(int damage) {
        baseDamage.set(damage);
    }

    // health

    public SimpleIntegerProperty health() {
        return health;
    }

    public void setHealth(int health) {
        this.health.set(health);
    }

    public int getHealth() {
        return health.get();
    }

    public void removeHealthPoints(int healthRemoved) {
        health.add(-healthRemoved);
    }

    // battle radius

    public SimpleIntegerProperty battleRadius() {
        return battleRadius;
    }

    public void setBattleRadius(int battleRadius) {
        this.battleRadius.set(battleRadius);
    }

    public int getBattleRadius() {
        return battleRadius.get();
    }

    // support radius
    public SimpleIntegerProperty supportRadius() {
        return supportRadius;
    }

    public void setSupportRadius(int supportRadius) {
        this.supportRadius.set(supportRadius);
    }

    public int getSupportRadius() {
        return supportRadius.get();
    }

    // Enemy Position Subjet
    public void attach(EnemyPositionObserver observer) {
        observers.add(observer);
    }

    public void detach(EnemyPositionObserver observer) {
        observers.remove(observer);
    }

    public void updateObservers() {
        for (EnemyPositionObserver observer : observers) {
            observer.encounter(this);
        }
    }

    public void setDroppableItems(List<Pair<Item, Double>> dropChances) {
        this.dropChances = dropChances;
    }

    public List<Item> getItemDrops() {
        Random chance = new Random(System.currentTimeMillis());
        List<Item> droppedItems = new ArrayList<Item>();

        // For each possible drop generate a random number to see if it actually drops
        for (int i = 0; i < dropChances.size(); i++) {
            Double percentChance = chance.nextDouble();
            if (percentChance <= dropChances.get(i).getValue1()) {
                droppedItems.add(dropChances.get(i).getValue0());
            }
        }
        
        return droppedItems;
    }
}
