package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.HelmetType;
import javafx.scene.image.Image;
import java.io.File;

public class Helmet extends EquippableItem implements HelmetType {
    private int reducedCharacterDamage;
    private int reducedEnemyDamage;

    // TODO write helmet
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedCharacterDamage = 1;
        reducedEnemyDamage = 3;
        setSellPrice(15);
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

    @Override
    public Image render() {
        return new Image((new File("src/images/helmet.png")).toURI().toString());
    }
}
