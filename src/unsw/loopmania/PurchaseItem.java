package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public interface PurchaseItem {
    public SimpleIntegerProperty getPrice();

    public SimpleStringProperty getDescription();
}
