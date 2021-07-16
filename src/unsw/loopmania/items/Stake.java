package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PurchaseItem;
import unsw.loopmania.enemies.Vampire;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stake extends EquippableItem implements PurchaseItem, CustomAttackStrategy {

    // TODO write stake
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void attack(BasicEnemy enemy) {
        System.out.println("inflict extra damage to vampires");
    }    

    public SimpleIntegerProperty getPrice() {
        return new SimpleIntegerProperty(5);
    }

    public SimpleStringProperty getDescription() {
        return new SimpleStringProperty("Stake");
    }
}
