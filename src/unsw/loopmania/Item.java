package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
public class Item extends StaticEntity {
    private int price;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
