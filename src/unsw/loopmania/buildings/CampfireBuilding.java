package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;
import unsw.loopmania.EnemyPositionObserver;
import unsw.loopmania.Helper;
import unsw.loopmania.Character;
import unsw.loopmania.CharacterEffect;
import javafx.scene.image.Image;
import java.io.File;

public class CampfireBuilding extends Building implements EnemyPositionObserver, CharacterEffect {
    // TODO: write campfire building
    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Doubles the damage of the character
     */
    public void affect(Character character) {
        if (Helper.withinRadius(this, character, 2)) {
            character.setDamage(character.getDamage() * 2);
        }
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
