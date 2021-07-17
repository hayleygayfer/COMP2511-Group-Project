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
public abstract class BasicEnemy extends MovingEntity implements DropLootStrategy, EnemyPositionSubject {
    
    // Enemy stats
    private SimpleIntegerProperty health = new SimpleIntegerProperty();
    private SimpleIntegerProperty damage = new SimpleIntegerProperty();
    private SimpleIntegerProperty battleRadius = new SimpleIntegerProperty();
    private SimpleIntegerProperty supportRadius = new SimpleIntegerProperty();

    private List<EnemyPositionObserver> observers = new ArrayList<EnemyPositionObserver>();
    private List<Pair<GenerateItem, Double>> dropItemChances;
    private List<Pair<GenerateCard, Double>> dropCardChances;

    private SimpleIntegerProperty experienceGained = new SimpleIntegerProperty();
    private SimpleIntegerProperty maxGoldGained = new SimpleIntegerProperty();

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

    public int getDamage() {
        return damage.get();
    }

    public SimpleIntegerProperty getDamageProperty() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage.set(damage);
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

    public void setExperienceGained(int xp) {
        this.experienceGained.set(xp);
    }

    public void setMaxGoldGained(int gold) {
        this.maxGoldGained.set(gold);
    }

    public void getXPAndGold(Character character) {
        Random chance = new Random(System.currentTimeMillis());
        int goldAmount = chance.nextInt(maxGoldGained.get());
        character.addGold(goldAmount);
        character.addXp(experienceGained.get());
    }

    public void setDroppableItems(List<Pair<GenerateItem, Double>> dropItemChances) {
        this.dropItemChances = dropItemChances;
    }

    public void setDroppableCards(List<Pair<GenerateCard, Double>> dropCardChances) {
        this.dropCardChances = dropCardChances;
    } 

    public List<GenerateItem> getItemDrops() {
        Random chance = new Random(System.currentTimeMillis());
        List<GenerateItem> droppedItems = new ArrayList<GenerateItem>();

        // For each possible drop generate a random number to see if it actually drops
        for (int i = 0; i < dropItemChances.size(); i++) {
            Double percentChance = chance.nextDouble();
            if (percentChance <= dropItemChances.get(i).getValue1()) {
                droppedItems.add(dropItemChances.get(i).getValue0());
            }
        }
        
        return droppedItems;
    }

    public void attack(Character character) {
        character.setModifiedHealth(character.getModifiedHealth() - getDamage());
    }

    public boolean isAlive() {
        return (getHealth() > 0);
    }

    public List<GenerateCard> getCardDrops() {
        Random chance = new Random(System.currentTimeMillis());
        List<GenerateCard> droppedCards = new ArrayList<GenerateCard>();

        // For each possible drop generate a random number to see if it actually drops
        for (int i = 0; i < dropCardChances.size(); i++) {
            Double percentChance = chance.nextDouble();
            if (percentChance <= dropCardChances.get(i).getValue1()) {
                droppedCards.add(dropCardChances.get(i).getValue0());
            }
        }
        
        return droppedCards;
    }
}
