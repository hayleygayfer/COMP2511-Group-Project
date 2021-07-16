package unsw.loopmania.shopItems;

import unsw.loopmania.Item;
import unsw.loopmania.ShopItem;
import unsw.loopmania.items.Shield;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class ShieldShopItem extends ShopItem {
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public ShieldShopItem() {
        this.description = new SimpleStringProperty("Shield");
        this.price = new SimpleIntegerProperty(5);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newShield = new Shield(x, y);
        return newShield;
    }

    public SimpleStringProperty description() {
        return description;
    }

    public SimpleIntegerProperty price() {
        return price;
    }

    public Image getImage() {
        return new Image((new File("src/images/shield.png")).toURI().toString());
    }

}