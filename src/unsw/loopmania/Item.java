package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class Item extends StaticEntity {
    private SimpleIntegerProperty sellPrice;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.sellPrice = new SimpleIntegerProperty(1);
    }

    public Image render() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }

    public void applyEffect(Character character) {

    }

    public void setSellPrice(int price) {
        sellPrice.set(price);
    }

    public SimpleIntegerProperty getSellPrice() {
        return sellPrice;
    }
}
