package unsw.loopmania.shopItems;

import unsw.loopmania.Item;
import unsw.loopmania.ShopItem;
import unsw.loopmania.items.Stake;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class StakeShopItem extends ShopItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public StakeShopItem() {
        this.name = new SimpleStringProperty("Stake");
        this.description = new SimpleStringProperty("Lower Damage, but very high damage against Vampires.");
        this.price = new SimpleIntegerProperty(15);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newStake = new Stake(x, y);
        return newStake;
    }

    public SimpleStringProperty description() {
        return description;
    }

    public SimpleIntegerProperty price() {
        return price;
    }

    public SimpleStringProperty name() {
        return name;
    }

    public Image getImage() {
        return new Image((new File("src/images/stake.png")).toURI().toString());
    }

}