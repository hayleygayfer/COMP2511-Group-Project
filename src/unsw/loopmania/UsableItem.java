package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class UsableItem extends Item implements UseItemStrategy {
    public UsableItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
