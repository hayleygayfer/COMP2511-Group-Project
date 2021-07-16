package unsw.loopmania.shopItems;

import unsw.loopmania.Item;
import unsw.loopmania.ShopItem;
import unsw.loopmania.items.Helmet;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class HelmetShopItem extends ShopItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public HelmetShopItem() {
        this.name = new SimpleStringProperty("Helmet");
        this.description = new SimpleStringProperty("Enemy damage is reduced, but character damage is also reduced.");
        this.price = new SimpleIntegerProperty(15);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newHelmet = new Helmet(x, y);
        return newHelmet;
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
        return new Image((new File("src/images/helmet.png")).toURI().toString());
    }

}