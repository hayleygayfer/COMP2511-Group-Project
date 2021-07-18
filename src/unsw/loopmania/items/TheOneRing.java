package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.enemies.Vampire;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.AccessoryType;
import javafx.scene.image.Image;
import java.io.File;

public class TheOneRing extends EquippableItem implements AccessoryType {
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(50);
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/the_one_ring.png")).toURI().toString());
    }
}
