package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.RareItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.generateItems.*;
import unsw.loopmania.enemies.Vampire;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;

public class Anduril extends EquippableItem implements WeaponType, RareItem {
    private GenerateItem itemInfo = new AndurilGenerateItem();

    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(50);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());
    }
}

