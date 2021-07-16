package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import java.io.File;

public class Gold extends StaticEntity implements CharacterPositionObserver {
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Character collects gold if they are at the same position
     * @param Character
     */
    public void encounter(Character character) {
        if (character.getX() == this.getX() && character.getY() == this.getY() && this.shouldExist().get()) {
            character.addGold(1);
            this.destroy();
        }
    }

    public Image render() {
        return new Image((new File("src/images/gold_pile.png")).toURI().toString());
    }
}