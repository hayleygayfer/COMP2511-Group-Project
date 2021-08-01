package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.ItemType;
import unsw.loopmania.ShopItem;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Helmet extends EquippableItem implements ShopItem {
    private int reducedEnemyDamage;
    private GenerateItem itemInfo = new HelmetGenerateItem();

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedEnemyDamage = 3;
        setSellPrice(15);
        setType(ItemType.HELMET);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    /**
     * Decreases the enemy's damage by a scalar value
     * @param enemy. The enemy to affect.
     */
    @Override
    public void affect(BasicEnemy enemy) {
        enemy.setDamage(enemy.getDamage() - reducedEnemyDamage);
    }

    // TODO: shouldn't this affect character as well?

    /**
     * returns a prices of 5 
     * @return SimpleIntegerProperty
     */
    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    /**
     * Gets the description of helemt
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Helmet");
    }

    @Override
    public String toString() {
        return "Helmet";
    }
}
