package unsw.loopmania.items;

import unsw.loopmania.RareItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.Item;
import unsw.loopmania.ItemType;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
<<<<<<< HEAD
import javafx.scene.image.Image;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

=======
>>>>>>> dev

public class DoggieCoin extends Item implements RareItem {
    private DoggieCoinGenerateItem itemInfo = new DoggieCoinGenerateItem();
    private Random rand = new Random(System.currentTimeMillis());

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(rand.nextInt(itemInfo.getUpperValue()));
        setType(ItemType.NOT_EQUIPPABLE);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }
}
