package unsw.loopmania.items;

import unsw.loopmania.RareItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.Item;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import java.io.File;

public class DoggieCoin extends Item implements RareItem {
    private GenerateItem itemInfo = new DoggieCoinGenerateItem();

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(50);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/doggiecoin.png")).toURI().toString());
    }
}
