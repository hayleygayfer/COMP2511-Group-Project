package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;

public class Stake extends EquippableItem implements WeaponType, CustomAttackStrategy {

    // TODO write stake
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(15);
    }

    public void attack(BasicEnemy enemy) {
        System.out.println("inflict extra damage to vampires");
    }    

    @Override
    public Image render() {
        return new Image((new File("src/images/stake.png")).toURI().toString());
    }
}
