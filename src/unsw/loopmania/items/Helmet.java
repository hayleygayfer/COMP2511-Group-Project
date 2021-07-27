package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.HelmetType;
import javafx.scene.image.Image;
import java.io.File;

public class Helmet extends EquippableItem implements HelmetType {
    private int reducedEnemyDamage;
    private GenerateItem itemInfo = new HelmetGenerateItem();

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedEnemyDamage = 3;
        setSellPrice(15);
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

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Helmet");
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/helmet.png")).toURI().toString());
    }
}
