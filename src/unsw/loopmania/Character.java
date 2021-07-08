package unsw.loopmania;

import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements CharacterPositionSubject {
    // inventory > list of items
    private List<Item> inventory;
    // damage strategy (what weapon is equipped)
    private DamageStrategy damageStrategy;
    private SimpleIntegerProperty health;
    private SimpleIntegerProperty baseDamage;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
    }

    public void addItemToInventory(Item item) {
    }

    public int getHealth() {
        return health.get();
    }

    public SimpleIntegerProperty health() {
        return health;
    }

    public int getDamage() {
        return damageStrategy.getModifiedDamage(baseDamage).get();
    }
    
}
