package test.items;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import org.javatuples.Pair;

import unsw.loopmania.items.Helmet;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.enemies.Slug;

public class HelmetTest {
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
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(helmet instanceof Helmet);
        assertTrue(helmet instanceof EquippableItem);
        assertTrue(helmet instanceof Item);
        assertTrue(helmet instanceof StaticEntity);
        assertTrue(helmet instanceof Entity);
    }

    @Test
    public void testPrice() {
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        // helmet costs 10 gold
        assertEquals(10, helmet.getPrice());
    }

    @Test
    public void testIsEquippable() {
        Character character = createCharacter();
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        assertTrue(helmet.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testDuplicateIsEquippable() {
        Character character = createCharacter();
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        character.equipItem(helmet1);

        Helmet helmet2 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));  

        // can't equip second helmet if helmet is already equipped
        assertFalse(helmet2.isEquippable(character.getEquippedItems())); 
        assertFalse(helmet1.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testGetModifiedDamage() {
        // reduces damage by 2
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int baseDamage = 10;
        assertEquals(baseDamage - 2, helmet.getModifiedDamage(slug, baseDamage));
    }

    @Test
    public void testGetModifiedEnemyDamage() {
        // reduces enemy damage by 2
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        int baseDamage = 10;
        assertEquals(baseDamage - 2, helmet.getModifiedEnemyDamage(baseDamage));
    }

    @Test
    public void testGetModifiedCriticalChance() {
        // does not modify critical chance
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        double baseCriticalChance = 0.2;

        assertEquals(baseCriticalChance, helmet.getModifiedCriticalChance(baseCriticalChance));
    }

    @Test
    public void attackSlug() {
        // attack should not do anything
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int initialHealth = slug.getHealth();

        helmet.attack(slug, 0);

        assertEquals(initialHealth, slug.getHealth());
    } 
}
