package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import javafx.scene.image.Image;
import java.io.File;

public class HerosCastleBuilding extends Building {
    public HerosCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public Image render() {
        return new Image((new File("src/images/heros_castle.png")).toURI().toString());
    }
}
