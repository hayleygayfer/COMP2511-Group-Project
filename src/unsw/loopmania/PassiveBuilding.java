package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class PassiveBuilding extends StaticEntity {
    public PassiveBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
