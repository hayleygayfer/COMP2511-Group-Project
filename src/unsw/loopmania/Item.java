package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
public class Item extends StaticEntity {
    private SimpleIntegerProperty price;
    private StringProperty description;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getPrice() {
        return price.get();
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleIntegerProperty price() {
        return price;
    }

    public StringProperty description() {
        return description;
    }

}
