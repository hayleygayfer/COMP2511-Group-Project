package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.AccessoryType;
import javafx.scene.image.Image;
import java.io.File;

public class TheOneRing extends EquippableItem implements AccessoryType {
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(50);
    }

    /**
     * Gets the image of the one ring for rendering
     * @return Image
     */
    @Override
    public Image render() {
        return new Image((new File("src/images/the_one_ring.png")).toURI().toString());
    }
}
