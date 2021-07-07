package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class BattleBuilding extends PassiveBuilding implements CharacterPositionObserver {
    public BattleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
