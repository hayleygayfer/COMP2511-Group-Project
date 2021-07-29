package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.ItemType;
import unsw.loopmania.ShopItem;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;

public class Shield extends EquippableItem implements ShopItem {
    private double reducedEnemyCriticalChance;
    private int reduceEnemyDamage;

    private GenerateItem itemInfo = new ShieldGenerateItem();

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedEnemyCriticalChance = 0.04;
        reduceEnemyDamage = 4;
        setSellPrice(10);
        setType(ItemType.SHIELD);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    /**
     * Reduces chance of critical vampire attacks by 60%
     * @param enemy The enemy to affect
     * @pre enemy != null
     */
    public void affect(BasicEnemy enemy) {
        enemy.setDamage(enemy.getDamage() - reduceEnemyDamage);
        if (enemy instanceof Vampire) {
            Vampire vampire = (Vampire) enemy;
            vampire.setCriticalHitChance(reducedEnemyCriticalChance);
        }
    }

    /**
     * returns a prices of 5 
     * @return SimpleIntegerProperty
     */
    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    /**
     * Gets the description of shield
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Shield");
    }

    /**
     * Gets the image of shield for rendering
     * @return Image
     */
    @Override
    public Image render() {
        return new Image((new File("src/images/shield.png")).toURI().toString());
    }
}
