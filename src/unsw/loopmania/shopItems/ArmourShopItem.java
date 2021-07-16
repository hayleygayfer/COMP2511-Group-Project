package unsw.loopmania.shopItems;

import unsw.loopmania.Item;
import unsw.loopmania.ShopItem;
import unsw.loopmania.items.Armour;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class ArmourShopItem extends ShopItem {
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public ArmourShopItem() {
        this.description = new SimpleStringProperty("Armour");
        this.price = new SimpleIntegerProperty(5);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newArmour = new Armour(x, y);
        return newArmour;
    }

    public SimpleStringProperty description() {
        return description;
    }

    public SimpleIntegerProperty price() {
        return price;
    }

    public Image getImage() {
        return new Image((new File("src/images/armour.png")).toURI().toString());
    }

}
