package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;

public class Staff extends EquippableItem implements CustomAttackStrategy, WeaponType{

    private int baseDamage;

    // TODO write staff
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        baseDamage = 2;
    }

    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        return this.baseDamage;
    }

    /**
     * Applies damage to target
     * Also has a chance of applying trance
     */
    public void attack(BasicEnemy enemy) {
        System.out.println("inflict trance");
    }

    @Override
    public Image getImage() {
        return new Image((new File("src/images/staff.png")).toURI().toString());
    }
}
