package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.WeaponType;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Character;
import unsw.loopmania.MovingEntity;
import javafx.scene.image.Image;
import java.io.File;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends EquippableItem implements WeaponType {
    private int damage;

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        damage = 10;
    }    

    @Override
    public void affect(Character character) {
        character.setBattleDamage(character.getBattleDamage() + damage);
    }

    @Override
    public Image getImage() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }
}
