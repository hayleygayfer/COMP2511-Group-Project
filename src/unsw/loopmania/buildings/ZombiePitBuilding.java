package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.SpawnEnemyStrategy;
import unsw.loopmania.Building;

public class ZombiePitBuilding extends Building implements SpawnEnemyStrategy {
    // TODO write zombie pit building
    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
