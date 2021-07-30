package unsw.loopmania.enemies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.BattleBehaviourContext;
import unsw.loopmania.Character;
import unsw.loopmania.CharacterEffect;
import unsw.loopmania.PathPosition;
import unsw.loopmania.EnemyPositionObserver;
import unsw.loopmania.Frame;
import unsw.loopmania.GenerateItem;

import org.javatuples.Pair;
import org.javatuples.Quintet;

public class ElanMuske extends BasicEnemy implements BattleBehaviourContext {
    private List<EnemyPositionObserver> observers = new ArrayList<EnemyPositionObserver>();
    private double criticalHitChance = 0.1;

    public ElanMuske(PathPosition position) {
        super(position);
        setDamage(10);
        setBattleRadius(2);
        setHealth(120);
        List<Pair<GenerateItem, Double>> droppableItems = new ArrayList<Pair<GenerateItem, Double>>();
        setDroppableItems(droppableItems);
        // xp and gold
        setMaxGoldGained(200);
        setExperienceGained(600);
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
     * Renders the image of the vampire.
     */
    public Image render() {
        return new Image((new File("src/images/ElanMuske.png")).toURI().toString());
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

    /**
     * Elon Musk Battle Behavviour
     */
    public List<Frame> battleBehaviour(List<BasicEnemy> enemies,
            List<CharacterEffect> buildings, BasicEnemy boss, Character character) {
        // TODO Auto-generated method stub
        return null;
    }
}

