package test.items;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.items.Shield;
import unsw.loopmania.Character;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.Entity;

public class ShieldTest {
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
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(shield instanceof Shield);
        assertTrue(shield instanceof EquippableItem);
        assertTrue(shield instanceof Item);
        assertTrue(shield instanceof StaticEntity);
        assertTrue(shield instanceof Entity);
    }

    @Test
    public void testPrice() {
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        // shield costs 10 gold
        assertEquals(10, shield.getPrice());
    }

    @Test
    public void testIsEquippable() {
        Character character = createCharacter();
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        assertTrue(shield.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testDuplicateIsEquippable() {
        Character character = createCharacter();
        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        character.equipItem(shield1);

        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));  

        // can't equip second shield if shield is already equipped
        assertFalse(shield2.isEquippable(character.getEquippedItems())); 
        assertFalse(shield1.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testGetModifiedDamage() {
        // shouldn't modify character's own damage
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int baseDamage = 2;
        assertEquals(baseDamage, shield.getModifiedDamage(slug, baseDamage));
    }

    @Test
    public void testGetModifiedEnemyDamage() {
        // doesn't affect enemy's damage
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        int baseDamage = 10;

        assertEquals(baseDamage, shield.getModifiedEnemyDamage(baseDamage));
    }

    @Test
    public void testGetModifiedCriticalChance() {
        // reduces critical hit from vampires by 60%
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        double baseCriticalChance = 0.2;

        assertEquals(baseCriticalChance * 0.4, shield.getModifiedCriticalChance(baseCriticalChance));
    }

    @Test
    public void attackSlug() {
        // attack should not do anything
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int initialHealth = slug.getHealth();

        shield.attack(slug, 10);

        assertEquals(initialHealth, slug.getHealth());
    } 
}
