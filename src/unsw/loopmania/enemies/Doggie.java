package unsw.loopmania.enemies;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;
import unsw.loopmania.EnemyPositionObserver;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.BossEnemyType;
import unsw.loopmania.generateItems.DoggieCoinGenerateItem;

import org.javatuples.Pair;

public class Doggie extends BasicEnemy implements BossEnemyType {
    private List<EnemyPositionObserver> observers = new ArrayList<EnemyPositionObserver>();
    private double criticalHitChance = 0.1;

    public Doggie(PathPosition position) {
        super(position);
        setDamage(5);
        setBattleRadius(2);
        setHealth(60);
        List<Pair<GenerateItem, Double>> droppableItems = new ArrayList<Pair<GenerateItem, Double>>();
        droppableItems.add(new Pair<GenerateItem, Double>(new DoggieCoinGenerateItem(), 1.0));
        setDroppableItems(droppableItems);
        // xp and gold
        setMaxGoldGained(50);
        setExperienceGained(300);
    }

    /**
     * Will reset the damage if is hit
     */
    @Override
    public void setDamage(int damage) {
        super.setDamage(damage);
    }

    /**
     * Sets critical hit chance
     * @param criticalHitChance The new critical hit chance
     */
    public void setCriticalHitChance(double criticalHitChance) {
      this.criticalHitChance = criticalHitChance;
    }

    public double getCriticalHitChance() {
        return this.criticalHitChance;
    }

    /**
     * Attaches an enemy position observer
     * @param observer The observer to attach
     */
    public void attach(EnemyPositionObserver observer) {
        observers.add(observer);
    }

    /**
     * Detaches an emepy position observer
     */
    public void detach(EnemyPositionObserver observer) {
        observers.remove(observer);
    }

    /**
     * Updates all enemy position observers
     */
    public void updateObservers() {
        for (EnemyPositionObserver observer : observers) {
            observer.encounter(this);
        }
    }
}
