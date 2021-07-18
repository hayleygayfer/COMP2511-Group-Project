package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.BasicEnemy;
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
        setSellPrice(20);
    }

    /**
     * Can't equip if armour is already equipped
     * @param equipped items a list of all already equipped items
     */
    @Override
    public boolean isEquippable(List<Item> equippedItems) {
        // TODO Auto-generated method stub
        return super.isEquippable(equippedItems);
    }

    /**
     * Halves the enemy attack damage,
     * @param enemy the enemy to affect
     */
    @Override
    public void affect(BasicEnemy enemy) {
        enemy.setDamage(enemy.getDamage() / 2);
    }

    /**
     * returns a prices of 5 
     * @return SimpleIntegerProperty
     */
    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    /**
     * Gets the description of Armour
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Armour");
    }
    
    /**
     * Gets the image of armour for rendering
     * @return Image
     */
    @Override
    public Image render() {
        return new Image((new File("src/images/armour.png")).toURI().toString());
    }
}
