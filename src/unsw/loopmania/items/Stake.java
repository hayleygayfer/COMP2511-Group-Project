package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.enemies.Vampire;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;

public class Stake extends EquippableItem implements WeaponType {
    private int baseDamage;

    // TODO write stake
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        baseDamage = 5;
    }

    @Override
    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        if (target.getClass() == Vampire.class) {
            return this.baseDamage * 3;
        } else return baseDamage;
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
        return new Image((new File("src/images/stake.png")).toURI().toString());
    }
}
