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

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newTheOneRing = new TheOneRing(x, y);
        return newTheOneRing;
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
        return new Image((new File("src/images/the_one_ring.png")).toURI().toString());
    }
}