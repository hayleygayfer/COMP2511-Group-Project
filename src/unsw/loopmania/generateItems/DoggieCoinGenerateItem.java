package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.ItemType;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.RareItem;
import unsw.loopmania.items.TreeStump;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class DoggieCoinGenerateItem extends GenerateItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public DoggieCoinGenerateItem() {
        this.name = new SimpleStringProperty("DoggieCoin");
        this.description = new SimpleStringProperty("A revolutionary asset type, which randomly fluctuates in sellable price to an extraordinary extent. Can sell at shop.");
        this.price = new SimpleIntegerProperty(50);
        setType(ItemType.NOT_EQUIPPABLE);
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
        return new Image((new File("src/images/doggiecoin.png")).toURI().toString());
    }
}
