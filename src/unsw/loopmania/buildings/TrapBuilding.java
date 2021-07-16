package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;
import unsw.loopmania.EnemyPositionObserver;
import javafx.scene.image.Image;
import java.io.File;

public class TrapBuilding extends Building implements EnemyPositionObserver {
    // TODO write trap building
    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void encounter(BasicEnemy enemy) {
        
    }

    public Image render() {
        return new Image((new File("src/images/trap.png")).toURI().toString());
    }
}
