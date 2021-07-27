package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;
import unsw.loopmania.CharacterPositionObserver;
import unsw.loopmania.EnemyPositionObserver;
import unsw.loopmania.Character;
import javafx.scene.image.Image;
import java.io.File;

public class CampfireBuilding extends Building implements CharacterPositionObserver, EnemyPositionObserver {
    // TODO: write campfire building
    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    /**
     * If character is on an adjacent tile, double damage is applied within the battle radius
     * @param character The current character
     * @pre character != null
     */
    public void encounter(Character character) {

    }

    /**
     * If a vampire moves within radius 2 of the campfire, they are moved back until they are out of its radius
     * @param enemy
     */
    public void encounter(BasicEnemy enemy) {
        // TODO Auto-generated method stub
        
    }

    /**
     * Creates a new image of campfire
     * @return Image
     */
    public Image render() {
        return new Image((new File("src/images/campfire.png")).toURI().toString());
    }
}
