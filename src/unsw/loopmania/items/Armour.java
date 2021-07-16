package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.Character;
import unsw.loopmania.itemTypes.ArmourType;
import javafx.scene.image.Image;
import java.io.File;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Armour extends EquippableItem implements ArmourType {

    // TODO write armour
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Can't equip if armour is already equipped
     */
    @Override
    public boolean isEquippable(List<Item> equippedItems) {
        // TODO Auto-generated method stub
        return super.isEquippable(equippedItems);
    }

    /**
     * Halves the enemy's prospective base damage,
     */
    @Override
    public int getModifiedEnemyDamage(int baseDamage) {
        // TODO Auto-generated method stub
        return baseDamage / 2;
    }

    @Override
    public Image getImage() {
        return new Image((new File("src/images/armour.png")).toURI().toString());
    }
}
