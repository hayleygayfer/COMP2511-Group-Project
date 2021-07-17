package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.enemies.Vampire;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.loopmania.itemTypes.ShieldType;
import javafx.scene.image.Image;
import java.io.File;
public class Shield extends EquippableItem implements ShieldType {
    private double reducedEnemyCriticalChance;

    // TODO write shield
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedEnemyCriticalChance = 0.40;
        setSellPrice(10);
    }

    /**
     * Reduces chance of critical vampire attacks by 60%
     * @param enemy The enemy to affect
     */
    public void affect(BasicEnemy enemy) {
        if (enemy instanceof Vampire) {
            Vampire vampire = (Vampire) enemy;
            vampire.setCriticalHitChance(reducedEnemyCriticalChance);
        }
    }

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Shield");
    }
    
    @Override
    public Image render() {
        return new Image((new File("src/images/shield.png")).toURI().toString());
    }
}
