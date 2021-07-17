package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.UsableItem;
import javafx.scene.image.Image;
import java.io.File;


public class HealthPotion extends UsableItem {
    // TODO write health potion
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(30);
    }

    /**
     * Increases health
     */
    @Override
    public void applyEffect(Character character) {
        character.setModifiedHealth(character.getBaseHealth());
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
    }
}
