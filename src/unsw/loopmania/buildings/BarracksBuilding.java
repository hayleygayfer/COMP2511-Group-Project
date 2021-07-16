package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.CharacterPositionObserver;
import unsw.loopmania.Character;
import javafx.scene.image.Image;
import java.io.File;

public class BarracksBuilding extends Building implements CharacterPositionObserver {
    // TODO write barracks building 
    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void encounter(Character character) {

    }

    public Image render() {
        return new Image((new File("src/images/barracks.png")).toURI().toString());
    }
}
