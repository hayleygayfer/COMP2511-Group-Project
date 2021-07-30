package unsw.loopmania.enemies;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.generateCards.TowerGenerateCard;
import unsw.loopmania.generateItems.TheOneRingGenerateItem;
import unsw.loopmania.generateItems.AndurilGenerateItem;
import unsw.loopmania.generateItems.TreeStumpGenerateItem;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;
import unsw.loopmania.PathPosition;
import unsw.loopmania.generateItems.StaffGenerateItem;

import org.javatuples.Pair;

public class Zombie extends BasicEnemy {

    public Zombie(PathPosition position) {
        super(position);
        setDamage(2);
        setBattleRadius(2);
        setHealth(10);
        List<Pair<GenerateItem, Double>> droppableItems = new ArrayList<Pair<GenerateItem, Double>>();
        droppableItems.add(new Pair<GenerateItem, Double>(new StaffGenerateItem(), 0.50));
        droppableItems.add(new Pair<GenerateItem, Double>(new HealthPotionGenerateItem(), 0.2));
        droppableItems.add(new Pair<GenerateItem, Double>(new TheOneRingGenerateItem(), 0.05));
        droppableItems.add(new Pair<GenerateItem, Double>(new AndurilGenerateItem(), 0.05));
        droppableItems.add(new Pair<GenerateItem, Double>(new TreeStumpGenerateItem(), 0.05));
        setDroppableItems(droppableItems);
        // card drops
        List<Pair<GenerateCard, Double>> droppableCards = new ArrayList<Pair<GenerateCard, Double>>();
        droppableCards.add(new Pair<GenerateCard, Double>(new TowerGenerateCard(), 0.50));
        setDroppableCards(droppableCards);
        // xp and gold
        setMaxGoldGained(4);
        setExperienceGained(10);
    }

    /**
     * The zombie moves every two ticks
     */
    @Override
    public void move(int tick) {
        if (tick % 2 == 0) {
            super.move(tick);
        }
    }
}