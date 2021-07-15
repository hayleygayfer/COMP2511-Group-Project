package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.PurchaseItem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Shield extends EquippableItem implements PurchaseItem {
    private double reducedEnemyCriticalChance;

    // TODO write shield
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedEnemyCriticalChance = 0.40;
    }

    /**
     * Chances of critical vampire attacks are reduced by 60%
     */
    @Override
    public double getModifiedCriticalChance(double baseCriticalChance) {
        return baseCriticalChance * reducedEnemyCriticalChance;
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Shield");
    }
}
