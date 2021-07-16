package unsw.loopmania.shopItems;

import unsw.loopmania.Item;
import unsw.loopmania.ShopItem;
import unsw.loopmania.items.Sword;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class SwordShopItem extends ShopItem {
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public SwordShopItem() {
        this.description = new SimpleStringProperty("Sword");
        this.price = new SimpleIntegerProperty(5);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newSword = new Sword(x, y);
        return newSword;
    }

    public SimpleStringProperty description() {
        return description;
    }

    public SimpleIntegerProperty price() {
        return price;
    }

    public Image getImage() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }

}
