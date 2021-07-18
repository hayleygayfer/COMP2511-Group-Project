package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.items.Shield;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class ShieldGenerateItem extends GenerateItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public ShieldGenerateItem() {
        this.name = new SimpleStringProperty("Shield");
        this.description = new SimpleStringProperty("Critical Vampire attacks have a 60% lower chance of occuring.");
        this.price = new SimpleIntegerProperty(10);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newShield = new Shield(x, y);
        return newShield;
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
        return new Image((new File("src/images/shield.png")).toURI().toString());
    }

}