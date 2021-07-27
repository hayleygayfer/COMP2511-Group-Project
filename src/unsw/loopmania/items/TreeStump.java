package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.RareItem;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.ShieldType;
import javafx.scene.image.Image;
import java.io.File;

public class TreeStump extends EquippableItem implements ShieldType, RareItem {
    private GenerateItem itemInfo = new TreeStumpGenerateItem();

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(50);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/tree_stump.png")).toURI().toString());
    }
}
