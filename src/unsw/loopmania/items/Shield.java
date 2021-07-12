package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import javafx.beans.property.SimpleIntegerProperty;
public class Shield extends EquippableItem {
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
}
