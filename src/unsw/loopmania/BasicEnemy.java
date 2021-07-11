package unsw.loopmania;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity implements DamageStrategy, DropLootStrategy {
    private SimpleIntegerProperty health;

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

    // This method should be overridden by specific enemies
    public SimpleIntegerProperty getModifiedDamage(SimpleIntegerProperty baseDamage) {
        return baseDamage;
    }

    public int getHealth() {
        return health.get();
    }
}
