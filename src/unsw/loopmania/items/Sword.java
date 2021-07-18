package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.WeaponType;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Character;
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
        setSellPrice(10);
    }    

    /**
     * Increases character damage
     * @param character the character to affect
     */
    @Override
    public void affect(Character character) {
        character.setModifiedDamage(character.getModifiedDamage() + damage);
    }

    /**
     * Gets the image of sword for rendering
     * @return Image
     */
    @Override
    public Image render() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }
}
