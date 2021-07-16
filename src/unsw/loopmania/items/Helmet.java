package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PurchaseItem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Helmet extends EquippableItem implements PurchaseItem {
    private int reducedCharacterDamage;
    private int reducedEnemyDamage;

    // TODO write helmet
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedCharacterDamage = 1;
        reducedEnemyDamage = 3;
    }

    /**
     * Decreases your damage
     */
    @Override
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        // TODO Auto-generated method stub
        return baseDamage - reducedCharacterDamage;
    }

    /**
     * Decreases the enemy's damage
     */
    @Override
    public int getModifiedEnemyDamage(int baseDamage) {
        // TODO Auto-generated method stub
        return baseDamage - reducedEnemyDamage;
    }

    /**
     * Decreases the enemy's damage by a scalar value
     * @param enemy. The enemy to affect.
     */
    @Override
    public void affect(BasicEnemy enemy) {
        enemy.setDamage(enemy.getDamage() - reducedEnemyDamage);
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Helmet");
    }
}
