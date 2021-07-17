package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.CharacterPositionObserver;
import unsw.loopmania.Character;
import javafx.scene.image.Image;
import java.io.File;

public class VillageBuilding extends Building {
    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    /**
     * When a character steps on the same tile, they gain 10 health points
     * @param character
     * @pre the character is not null
     * @post the character has not been modified in any other way except its health points
     */
    public void encounter(Character character) {
        if (character.getX() == getX() && character.getY() == getY()) {
            character.gainHealth(10);
        }
    }

    public Image render() {
        return new Image((new File("src/images/village.png")).toURI().toString());
    }
}
