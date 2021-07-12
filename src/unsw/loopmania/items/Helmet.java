package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;

public class Helmet extends EquippableItem {
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
}
