package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import javafx.beans.property.SimpleIntegerProperty;
public class Shield extends EquippableItem {
    // TODO write shield
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Chances of critical vampire attacks are reduced by 60%
     */
    @Override
    public double getModifiedCriticalChance(double baseCriticalChance) {
        // TODO Auto-generated method stub
        return super.getModifiedCriticalChance(baseCriticalChance);
    }
}
