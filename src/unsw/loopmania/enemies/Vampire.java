package unsw.loopmania.enemies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;
import unsw.loopmania.generateItems.StakeGenerateItem;
import unsw.loopmania.generateItems.TheOneRingGenerateItem;
import unsw.loopmania.generateItems.AndurilGenerateItem;
import unsw.loopmania.generateItems.TreeStumpGenerateItem;
import unsw.loopmania.generateItems.ShieldGenerateItem;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.generateCards.VillageGenerateCard;
import unsw.loopmania.generateCards.CampfireGenerateCard;

import org.javatuples.Pair;

public class Vampire extends BasicEnemy {

    private double criticalHitChance = 0.1;

    public Vampire(PathPosition position) {
        super(position);
        setDamage(3);
        setBattleRadius(2);
        setHealth(15);
        List<Pair<GenerateItem, Double>> droppableItems = new ArrayList<Pair<GenerateItem, Double>>();
        droppableItems.add(new Pair<GenerateItem, Double>(new StakeGenerateItem(), 0.50));
        droppableItems.add(new Pair<GenerateItem, Double>(new ShieldGenerateItem(), 0.50));
        droppableItems.add(new Pair<GenerateItem, Double>(new HealthPotionGenerateItem(), 0.2));
        droppableItems.add(new Pair<GenerateItem, Double>(new TheOneRingGenerateItem(), 0.05));
        droppableItems.add(new Pair<GenerateItem, Double>(new AndurilGenerateItem(), 0.05));
        droppableItems.add(new Pair<GenerateItem, Double>(new TreeStumpGenerateItem(), 0.05));
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
     * @param damage Updating damage
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

    /**
     * Gets the chance of a critical hit 
     * @return double 
     */
    public double getCriticalHitChance() {
        return this.criticalHitChance;
    }

    /**
     * Renders the image of the vampire.
     * @return Image
     */
    public Image render() {
        return new Image((new File("src/images/vampire.png")).toURI().toString());
    }

}
