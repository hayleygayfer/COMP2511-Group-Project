package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.RareItem;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.AccessoryType;
import javafx.scene.image.Image;
import java.io.File;

public class TheOneRing extends EquippableItem implements AccessoryType, RareItem {
    private GenerateItem itemInfo = new TheOneRingGenerateItem();

    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(50);
    }

    /**
     * Gets the image of the one ring for rendering
     * @return Image
     */
    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/the_one_ring.png")).toURI().toString());
    }
}
