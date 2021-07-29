package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import java.io.File;

public class Item extends StaticEntity {
    private SimpleIntegerProperty sellPrice;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.sellPrice = new SimpleIntegerProperty(1);
    }

    /**
     * TODO: change to make generic for all item types
     * Creates a new image of a sword for rendering 
     * @return Image
     */
    public Image render() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }

    public GenerateItem getItemDetails() {
        return null;
    }

    /**
     * Sell item price setter
     * @param price The price to sell an item
     */
    public void setSellPrice(int price) {
        sellPrice.set(price);
    }

    /**
     * Sell item price getter
     * @return SimpleIntegerProperty
     */
    public SimpleIntegerProperty getSellPrice() {
        return sellPrice;
    }
}
