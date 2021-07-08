package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class EncounterBuilding extends StaticEntity implements CharacterPositionObserver, EnemyPositionObserver{
    public EncounterBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void updateCharacterPosition() {
        //TODO
    }

    public void updateEnemyPosition(EnemyPositionSubject subject) {
        //TODO
    }

    public void encounter (MovingEntity character) {

    }
}
 