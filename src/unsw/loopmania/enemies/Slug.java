package unsw.loopmania.enemies;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Item;
import unsw.loopmania.items.Sword;

import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

public class Slug extends BasicEnemy {
    // TODO write slug
    public Slug(PathPosition position) {
        super(position);
        setDamage(1);
        setBattleRadius(1);
        setHealth(1);
        // add droppable items
        List<Pair<Item, Double>> droppableItems = new ArrayList<Pair<Item, Double>>();
        setDroppableItems(droppableItems);
    }
}
