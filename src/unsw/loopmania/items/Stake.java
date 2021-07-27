package unsw.loopmania.items;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.generateItems.*;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;

public class Stake extends EquippableItem implements WeaponType, CustomAttackStrategy {
    private int baseDamage;

    private GenerateItem itemInfo = new StakeGenerateItem();

    // TODO write stake
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(15);
        // has low normal stats
        baseDamage = 5;
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    public int getModifiedDamage(MovingEntity target, int baseDamage) {
        return this.baseDamage + baseDamage;
    }

    public void attack(BasicEnemy enemy) {
        System.out.println("inflict extra damage to vampires");
        if (enemy instanceof Vampire) {
            int vampireDamage = 10;
            Vampire vampire = (Vampire) enemy;
            vampire.setDamage(getModifiedDamage(vampire, vampireDamage));
        } else {
            enemy.setDamage(getModifiedDamage(enemy, baseDamage));
        }
    }    

    @Override
    public Image render() {
        return new Image((new File("src/images/stake.png")).toURI().toString());
    }
}
