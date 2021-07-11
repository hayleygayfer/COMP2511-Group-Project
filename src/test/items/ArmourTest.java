package test.items;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.items.Armour;
import unsw.loopmania.Character;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.Entity;

public class ArmourTest {
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
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(armour instanceof Armour);
        assertTrue(armour instanceof EquippableItem);
        assertTrue(armour instanceof Item);
        assertTrue(armour instanceof StaticEntity);
        assertTrue(armour instanceof Entity);
    }

    @Test
    public void testIsEquippable() {
        Character character = createCharacter();
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        assertTrue(armour.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testDuplicateIsEquippable() {
        Character character = createCharacter();
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        character.equipItem(armour1);

        Armour armour2 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));  

        // can't equip second armour if armour is already equipped
        assertFalse(armour2.isEquippable(character.getEquippedItems())); 
        assertFalse(armour1.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testGetModifiedDamage() {
        // shouldn't modify character's own damage
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int baseDamage = 2;
        assertEquals(baseDamage, armour.getModifiedDamage(slug, baseDamage));
    }

    @Test
    public void testGetModifiedEnemyDamage() {
        // halves the enemy's base damage
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        int baseDamage = 10;

        assertEquals(5, armour.getModifiedEnemyDamage(baseDamage));
    }

    @Test
    public void testGetModifiedCriticalChance() {
        // does not modify critical chance
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        double baseCriticalChance = 0.2;

        assertEquals(baseCriticalChance, armour.getModifiedCriticalChance(baseCriticalChance));
    }

    @Test
    public void attackSlug() {
        // attack should not do anything
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int initialHealth = slug.getHealth();

        armour.attack(slug, 10);

        assertEquals(initialHealth, slug.getHealth());
    }
}
