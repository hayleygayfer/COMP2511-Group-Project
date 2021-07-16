package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class Building extends StaticEntity implements SpawnEnemyStrategy {
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public BasicEnemy possiblySpawnEnemy(List<Pair<Integer, Integer>> orderedPath, int gameCycle) {
        return null;
    }
} 
