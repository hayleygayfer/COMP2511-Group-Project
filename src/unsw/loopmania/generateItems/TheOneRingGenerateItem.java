package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.items.TheOneRing;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class TheOneRingGenerateItem extends GenerateItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public TheOneRingGenerateItem() {
        this.name = new SimpleStringProperty("The One Ring");
        this.description = new SimpleStringProperty("Player Respawns with full health one time.");
        this.price = new SimpleIntegerProperty(50);
    }

   /**
     * Creates a new the one ring item based on its new position
     * @param x x coordinate of position
     * @param y y coordinate of position
     * @return Item a new item
     */
    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newTheOneRing = new TheOneRing(x, y);
        return newTheOneRing;
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
     * Gets the image of one ring for rendering
     * @return Image
     */
    public Image getImage() {
        return new Image((new File("src/images/the_one_ring.png")).toURI().toString());
    }
}