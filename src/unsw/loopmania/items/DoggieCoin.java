package unsw.loopmania.items;

import unsw.loopmania.RareItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.Item;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class DoggieCoin extends Item implements RareItem {
    private DoggieCoinGenerateItem itemInfo = new DoggieCoinGenerateItem();
    private Random rand = new Random(System.currentTimeMillis());

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(rand.nextInt(itemInfo.getUpperValue()));
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
