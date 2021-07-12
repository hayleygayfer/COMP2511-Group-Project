package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;
import unsw.loopmania.EnemyPositionObserver;

public class TrapBuilding extends Building implements EnemyPositionObserver {
    // TODO write trap building
    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void encounter(BasicEnemy enemy) {
        
    }
}
