package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.items.Sword;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class SwordGenerateItem extends GenerateItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public SwordGenerateItem() {
        this.name = new SimpleStringProperty("Sword");
        this.description = new SimpleStringProperty("Increases damage dealt by the character.");
        this.price = new SimpleIntegerProperty(10);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newSword = new Sword(x, y);
        return newSword;
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
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }

}
