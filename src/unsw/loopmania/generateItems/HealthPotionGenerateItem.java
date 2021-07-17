package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.items.HealthPotion;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class HealthPotionGenerateItem extends GenerateItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public HealthPotionGenerateItem() {
        this.name = new SimpleStringProperty("Health Potion");
        this.description = new SimpleStringProperty("Restores character health");
        this.price = new SimpleIntegerProperty(20);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newHealthPotion = new HealthPotion(x, y);
        return newHealthPotion;
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
        return new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
    }

}