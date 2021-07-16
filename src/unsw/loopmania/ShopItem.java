package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public abstract class ShopItem {
    public abstract Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y);
    public abstract SimpleStringProperty description();
    public abstract SimpleIntegerProperty price();
    public abstract Image getImage();
}
