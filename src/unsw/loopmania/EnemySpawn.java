package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class EnemySpawn extends PassiveBuilding implements GameCycleObserver {
    public EnemySpawn(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
