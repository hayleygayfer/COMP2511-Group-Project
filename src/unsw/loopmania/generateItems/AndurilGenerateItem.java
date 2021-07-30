package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.RareItem;
import unsw.loopmania.itemTypes.WeaponType;
import unsw.loopmania.items.TreeStump;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class AndurilGenerateItem extends GenerateItem implements RareItem, WeaponType {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public AndurilGenerateItem() {
        this.name = new SimpleStringProperty("Aduril, Flame of the West");
        this.description = new SimpleStringProperty("A very high damage sword which causes triple damage against bosses.");
        this.price = new SimpleIntegerProperty(50);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newTreeStump = new TreeStump(x, y);
        return newTreeStump;
    }

    public SimpleStringProperty description() {
        return description;
    }

    public SimpleStringProperty name() {
        return name;
    }

    public SimpleIntegerProperty price() {
        return price;
    }

    public Image getImage() {
        return new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());
    }
}