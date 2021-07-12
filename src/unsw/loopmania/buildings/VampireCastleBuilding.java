package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.SpawnEnemyStrategy;
import unsw.loopmania.enemies.Vampire;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building implements SpawnEnemyStrategy {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(y, y);
    }

    /**
     * Spawns Vampire
     */
    public Vampire spawnEnemy() {
        return null;
    }
}
