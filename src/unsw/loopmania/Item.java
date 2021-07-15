package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
public class Item extends StaticEntity {
    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
