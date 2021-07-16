package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;
import org.javatuples.Pair;
import unsw.loopmania.SpawnEnemyStrategy;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.CharacterPositionObserver;

public class ZombiePitBuilding extends Building implements SpawnEnemyStrategy {
    // TODO write zombie pit building
    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Generates a zombie every cycle
     * @pre the character is currently at hero's castle
     */
    public Zombie possiblySpawnEnemy(List<Pair<Integer, Integer>> orderedPath, int gameCycle) {
        return null;
    }

   
}
