package test.items;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import unsw.loopmania.items.Sword;
import unsw.loopmania.items.Stake;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;

public class SwordTest {
    /**
     * Creates the path for testing
     * just a 5x5 square loop
     */
    public List<Pair<Integer, Integer>> createPath() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

        // add top horizontal
        for (int i = 0; i < 5; i++) {
            orderedPath.add(Pair.with(0, i));
        }
        // add right side down
        for (int i = 1; i < 5; i++) {
            orderedPath.add(Pair.with(i, 4));
        }
        // add bottom horizontal
        for (int i = 4; i >= 0; i--) {
            orderedPath.add(Pair.with(4, i));
        }
        // add left side up
        for (int i = 4; i >= 0; i--) {
            orderedPath.add(Pair.with(i, 0));
        }

        return orderedPath;
    }

    public Character createCharacter() {
        return new Character(new PathPosition(0, createPath()));
    }

    @Test
    public void testConstructor() {
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(sword instanceof Sword);
        assertTrue(sword instanceof EquippableItem);
        assertTrue(sword instanceof Item);
        assertTrue(sword instanceof StaticEntity);
        assertTrue(sword instanceof Entity);
    }

    @Test
    public void testIsEquippable() {
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Character character = createCharacter();

        assertTrue(sword.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testIsEquippableWithOtherAttackItems() {
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Character character = createCharacter(); 

        character.equipItem(stake);

        // can't equip sword item if another attack item is equipped
        assertFalse(sword.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testDuplicateIsEquippable() {
        Sword sword1 = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));  
        Character character = createCharacter(); 

        character.equipItem(sword1);

        Sword sword2 = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));   

        // can't equip sword if another sword is equipped
        assertFalse(sword2.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testGetModifiedDamage() {
        // sets damage to 10
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        int baseDamage = 2;

        Slug slug = new Slug(new PathPosition(0, createPath()));
        Zombie zombie = new Zombie(new PathPosition(0, createPath()));
        Vampire vampire = new Vampire(new PathPosition(0, createPath()));

        assertEquals(10, sword.getModifiedDamage(slug, baseDamage));
        assertEquals(10, sword.getModifiedDamage(zombie, baseDamage));
        assertEquals(10, sword.getModifiedDamage(vampire, baseDamage));
    }

    @Test
    public void testGetModifiedEnemyDamage() {
        // won't affect enemy damage
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        int baseDamage = 2;
        assertEquals(baseDamage, sword.getModifiedEnemyDamage(baseDamage));
    }

    @Test
    public void testGetModifiedCriticalChance() {
        // doesn't modify critical chance
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        double baseCriticalChance = 0.2;

        assertEquals(baseCriticalChance, sword.getModifiedCriticalChance(baseCriticalChance));
    }

    @Test
    public void attackZombie() {
        // decrease zombie health by 10
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Zombie zombie = new Zombie(new PathPosition(0, createPath()));

        int initialHealth = zombie.getHealth();

        sword.attack(zombie, 10);

        assertEquals(initialHealth - 10, zombie.getHealth()); 
    } 
}
