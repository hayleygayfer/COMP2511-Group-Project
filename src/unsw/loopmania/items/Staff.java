package unsw.loopmania.items;

import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;
import java.util.Random;

public class Staff extends EquippableItem implements CustomAttackStrategy, WeaponType{

    private int baseDamage;

    // TODO write staff
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        baseDamage = 2;
        setSellPrice(20);
    }

    /**
     * Applies damage to target
     * Also has a chance of applying trance
     */
    public void attack(BasicEnemy enemy) {
        System.out.println("inflict trance");
        Random random = new Random();
        int chance = random.nextInt(100);
        if (applyTrance(chance)) {
            // Destroy enemy 
            
            // Turn enemy into allied soldier
            // Set how long a trance lasts for 
            // If that time has passed then turn allied soldier back into enemy
            // If the time is still going during the fight enemy dies
        }
        enemy.setDamage(enemy.getDamage() + baseDamage);
    }

    private boolean applyTrance(int chance) {
        if (chance >= 60) {
            return true;
        } else if (chance < 60) {
            return false;
        }
        return false;
    }

    @Override
    public Image render() {
        return new Image((new File("src/images/staff.png")).toURI().toString());
    }
}
