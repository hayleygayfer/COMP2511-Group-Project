package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

// I don't think this class needs to exist?
public abstract class PassiveBuilding extends StaticEntity {
    public PassiveBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
