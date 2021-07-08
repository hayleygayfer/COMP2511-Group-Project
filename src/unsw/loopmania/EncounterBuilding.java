package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class EncounterBuilding extends StaticEntity {
    public EncounterBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
