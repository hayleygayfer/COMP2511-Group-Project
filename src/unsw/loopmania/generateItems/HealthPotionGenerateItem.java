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
        this.price = new SimpleIntegerProperty(30);
    }

    /**
     * Creates a new health potion item based on its new position
     * @param x x coordinate of position
     * @param y y coordinate of position
     * @return Item a new item
     */
    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newHealthPotion = new HealthPotion(x, y);
        return newHealthPotion;
    }

    /**
     * Gets the description for the label
     * @return SimpleStringProperty
     */
    public SimpleStringProperty description() {
        return description;
    }

    /**
     * Gets the name for the label
     * @return SimpleStringProperty
     */
    public SimpleStringProperty name() {
        return name;
    }

    /**
     * Gets the price for the label
     * @return SimpleStringProperty
     */
    public SimpleIntegerProperty price() {
        return price;
    }

    /**
     * Gets the image of health potion for rendering
     * @return Image
     */
    public Image getImage() {
        return new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
    }

}