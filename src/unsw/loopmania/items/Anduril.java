package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import unsw.loopmania.RareItem;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.ItemType;
import unsw.loopmania.generateItems.*;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.CustomAttackStrategy;
import unsw.loopmania.BossEnemyType;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Character;
import javafx.scene.image.Image;
import java.io.File;

public class Anduril extends EquippableItem implements RareItem, CustomAttackStrategy {
    private GenerateItem itemInfo = new AndurilGenerateItem();
    private int damage;

    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setSellPrice(50);
        this.damage = 20;
        setType(ItemType.WEAPON);
    }

    @Override
    public GenerateItem getItemDetails() {
        return itemInfo;
    }

    public void attack(BasicEnemy enemy, Character character) {
        if (enemy instanceof BossEnemyType) {
            enemy.setHealth(enemy.getHealth() - damage * 3);
        } else {
            enemy.setHealth(enemy.getHealth() - damage);
        }
    }

    /**
     * Increases character damage
     * @param character the character to affect
     */
    @Override
    public void affect(Character character) {
        character.setDamage(character.getDamage() + damage);
    }


    @Override
    public Image render() {
        return new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());
    }
}

