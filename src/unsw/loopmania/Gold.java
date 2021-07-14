package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Gold extends StaticEntity implements CharacterPositionObserver {
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Character collects gold if they are at the same position
     * @param Character
     */
    public void encounter(Character character) {
        if (character.getX() == this.getX() && character.getY() == this.getY()) {
            collect(character);
        }
    }

    /**
     * When gold is collected, add the corresponding value to the character's total gold
     * @param Character
     */
    public void collect(Character character) {
        character.addGold(1);
    }
}