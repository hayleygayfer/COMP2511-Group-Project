package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.WeaponType;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.scene.image.Image;
import java.io.File;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends EquippableItem implements WeaponType {
    private int baseDamage;

    // TODO write sword
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        baseDamage = 10;
    }    

    /**
     * Given a character's base damage, returns the modified
     * damage based on the properties of this item
     */
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        return this.baseDamage;
    }

    /**
     * Applies damage to the target
     */
    @Override
    public void attack(MovingEntity target, int damage) {
        // TODO Auto-generated method stub
        super.attack(target, damage);
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }
}
