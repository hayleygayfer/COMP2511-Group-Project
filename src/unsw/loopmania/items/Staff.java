package unsw.loopmania.items;

import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.generateItems.*;
import unsw.loopmania.MovingEntity;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.itemTypes.WeaponType;
import javafx.scene.image.Image;
import java.io.File;
import java.util.Random;

public class Staff extends EquippableItem implements CustomAttackStrategy, WeaponType{

    private int baseDamage;

    private GenerateItem itemInfo = new StaffGenerateItem();

    // TODO write staff
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        baseDamage = 1;
        setSellPrice(20);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    /**
     * Applies damage to target
     * Also has a chance of applying trance
     * @param enemy The enemy to affect
     * @pre enemy != null
     */
    public void attack(BasicEnemy enemy, Character character) {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (applyTrance(chance)) {
            enemy.setHealth(0);
            character.addSoldier(new AlliedSoldier(character, true));
        }
        enemy.setDamage(enemy.getDamage() + baseDamage);
    }

    /**
     * Checks if the random number is above 60 - then puts the returns true to put enemy
     * into trance
     * If below 60 then does not put enemy into trance
     * @param chance A random number between 0-100
     * @pre chance != null
     * @return boolean
     */
    private boolean applyTrance(int chance) {
        if (chance >= 60) {
            return true;
        } else if (chance < 60) {
            return false;
        }
        return false;
    }

    /**
     * Gets the image of staff for rendering
     * @return Image
     */
    @Override
    public Image render() {
        return new Image((new File("src/images/staff.png")).toURI().toString());
    }
}
