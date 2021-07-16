package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.PurchaseItem;
import unsw.loopmania.enemies.Vampire;
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

    /**
     * Reduces chance of critical vampire attacks by 60%
     * @param enemy The enemy to affect
     */
    public void affect(BasicEnemy enemy) {
        if (enemy instanceof Vampire) {
            Vampire vampire = (Vampire) enemy;
            vampire.setCriticalHitChance(reducedEnemyCriticalChance);
        }
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Shield");
    }
}
