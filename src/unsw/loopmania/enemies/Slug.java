package unsw.loopmania.enemies;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;
import unsw.loopmania.generateItems.SwordGenerateItem;
import unsw.loopmania.generateItems.TheOneRingGenerateItem;
import unsw.loopmania.generateItems.AndurilGenerateItem;
import unsw.loopmania.generateItems.TreeStumpGenerateItem;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.generateCards.BarracksGenerateCard;

import org.javatuples.Pair;

public class Slug extends BasicEnemy {

    public Slug(PathPosition position) {
        super(position);
        setDamage(3);
        setBattleRadius(1);
        setHealth(4);
        setSupportRadius(100);
        // item drops
        List<Pair<GenerateItem, Double>> droppableItems = new ArrayList<Pair<GenerateItem, Double>>();
        droppableItems.add(new Pair<GenerateItem, Double>(new SwordGenerateItem(), 0.50));
        droppableItems.add(new Pair<GenerateItem, Double>(new HealthPotionGenerateItem(), 0.2));
        droppableItems.add(new Pair<GenerateItem, Double>(new TheOneRingGenerateItem(), 0.05));
        droppableItems.add(new Pair<GenerateItem, Double>(new AndurilGenerateItem(), 0.05));
        droppableItems.add(new Pair<GenerateItem, Double>(new TreeStumpGenerateItem(), 0.05));
        setDroppableItems(droppableItems);
        // card drops
        List<Pair<GenerateCard, Double>> droppableCards = new ArrayList<Pair<GenerateCard, Double>>();
        droppableCards.add(new Pair<GenerateCard, Double>(new BarracksGenerateCard(), 0.50));
        setDroppableCards(droppableCards);
        // xp and gold
        setMaxGoldGained(2);
        setExperienceGained(5);
    }
}
