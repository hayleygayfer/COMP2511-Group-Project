package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;

public class Stake extends EquippableItem implements WeaponType, CustomAttackStrategy {
    // private int baseDamage;

    // TODO write stake
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void attack(BasicEnemy enemy) {
        System.out.println("inflict extra damage to vampires");
    }    

    @Override
    public Image getImage() {
        return new Image((new File("src/images/stake.png")).toURI().toString());
    }
}
