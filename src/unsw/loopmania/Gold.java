package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Gold extends StaticEntity {
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * When gold is collected, add the corresponding value to the character's total gold
     * @param Character
     */
    public void collect(Character character) {

    }
}