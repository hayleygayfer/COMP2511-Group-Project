package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class Building extends PassiveBuilding {
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
