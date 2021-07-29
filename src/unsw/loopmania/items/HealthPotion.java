package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.CharacterEffect;
import unsw.loopmania.UsableItem;
import javafx.scene.image.Image;
import java.io.File;


public class HealthPotion extends UsableItem {
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(30);
    }

    /**
     * Increases health
     * @param character The character which its going increase the health for
     * @pre character != null
     */
    public void affect(Character character) {
        character.setCurrentHealth(character.getBaseHealth());
    }

    /**
     * Gets the image of health potion for rendering
     * @return Image
     */
    @Override
    public Image render() {
        return new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
    }
}
