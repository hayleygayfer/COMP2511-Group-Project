package unsw.loopmania.shopItems;

import unsw.loopmania.Item;
import unsw.loopmania.ShopItem;
import unsw.loopmania.items.Staff;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class StaffShopItem extends ShopItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;

    public StaffShopItem() {
        this.name = new SimpleStringProperty("Staff");
        this.description = new SimpleStringProperty("Very low damage, but can randomly inflict a trance on Enemies to turn them into Allied Soldiers");
        this.price = new SimpleIntegerProperty(5);
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newStaff = new Staff(x, y);
        return newStaff;
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
        return new Image((new File("src/images/staff.png")).toURI().toString());
    }

}
