package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.WeaponType;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.generateItems.*;
import unsw.loopmania.Character;
import unsw.loopmania.MovingEntity;
import javafx.scene.image.Image;
import java.io.File;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends EquippableItem implements WeaponType {
    private int damage;

    private GenerateItem itemInfo = new SwordGenerateItem();

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        damage = 10;
        setSellPrice(10);
    } 
    
    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    @Override
    public void affect(Character character) {
        character.setModifiedDamage(character.getModifiedDamage() + damage);
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }
}
