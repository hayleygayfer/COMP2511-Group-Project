package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.CharacterPositionObserver;
import unsw.loopmania.Character;

public class VillageBuilding extends Building implements CharacterPositionObserver {
    // TODO write village building
    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    public void encounter(Character character) {

    }
}
