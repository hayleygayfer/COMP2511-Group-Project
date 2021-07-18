package unsw.loopmania.enemies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;
import unsw.loopmania.EnemyPositionObserver;
import unsw.loopmania.generateItems.StakeGenerateItem;
import unsw.loopmania.generateItems.TheOneRingGenerateItem;
import unsw.loopmania.generateItems.ShieldGenerateItem;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.generateCards.VillageGenerateCard;
import unsw.loopmania.generateCards.CampfireGenerateCard;

import org.javatuples.Pair;

public class Vampire extends BasicEnemy {

    private List<EnemyPositionObserver> observers = new ArrayList<EnemyPositionObserver>();
    private double criticalHitChance = 0.1;

    // TODO write vampire
    public Vampire(PathPosition position) {
        super(position);
<<<<<<< HEAD
        setDamage(1);
        System.out.println("damage"  + getDamage());
=======
        setDamage(3);
>>>>>>> dev
        setBattleRadius(2);
        setHealth(15);
        List<Pair<GenerateItem, Double>> droppableItems = new ArrayList<Pair<GenerateItem, Double>>();
        droppableItems.add(new Pair<GenerateItem, Double>(new StakeGenerateItem(), 0.50));
        droppableItems.add(new Pair<GenerateItem, Double>(new ShieldGenerateItem(), 0.50));
        droppableItems.add(new Pair<GenerateItem, Double>(new HealthPotionGenerateItem(), 0.2));
        droppableItems.add(new Pair<GenerateItem, Double>(new TheOneRingGenerateItem(), 0.05));
        setDroppableItems(droppableItems);
        // card drops
        List<Pair<GenerateCard, Double>> droppableCards = new ArrayList<Pair<GenerateCard, Double>>();
        droppableCards.add(new Pair<GenerateCard, Double>(new VillageGenerateCard(), 0.50));
        droppableCards.add(new Pair<GenerateCard, Double>(new CampfireGenerateCard(), 0.50));
        setDroppableCards(droppableCards);
        // xp and gold
        setMaxGoldGained(8);
        setExperienceGained(20);
    }

    /**
     * Will reset the damage if is hit
     */
    @Override
    public void setDamage(int damage) {
        // TODO Auto-generated method stub
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
     * Renders the image of the vampire.
     */
    public Image render() {
        return new Image((new File("src/images/vampire.png")).toURI().toString());
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
