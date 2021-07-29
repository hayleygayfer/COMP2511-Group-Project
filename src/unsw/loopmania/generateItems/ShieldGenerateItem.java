package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.itemTypes.ShieldType;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.items.Shield;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class ShieldGenerateItem extends GenerateItem implements ShieldType {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public ShieldGenerateItem() {
        this.name = new SimpleStringProperty("Shield");
        this.description = new SimpleStringProperty("Critical Vampire attacks have a 60% lower chance of occuring.");
        this.price = new SimpleIntegerProperty(10);
    }

    /**
     * Creates a new shield item based on its new position
     * @param x x coordinate of position
     * @param y y coordinate of position
     * @return Item a new item
     */
    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newShield = new Shield(x, y);
        return newShield;
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
     * Gets the image of shield for rendering
     * @return Image
     */
    public Image getImage() {
        return new Image((new File("src/images/shield.png")).toURI().toString());
    }

}