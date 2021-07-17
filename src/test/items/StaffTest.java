package test.items;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import unsw.loopmania.items.Staff;
import unsw.loopmania.items.Sword;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.enemies.Slug;

public class StaffTest {
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
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(staff instanceof Staff);
        assertTrue(staff instanceof EquippableItem);
        assertTrue(staff instanceof Item);
        assertTrue(staff instanceof StaticEntity);
        assertTrue(staff instanceof Entity);
    }

    @Test
    public void testIsEquippable() {
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Character character = createCharacter();

        assertTrue(staff.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testIsEquippableWithOtherAttackItems() {
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Character character = createCharacter(); 

        character.equipItem(sword);

        // can't equip staff item if another attack item is equipped
        assertFalse(staff.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testDuplicateIsEquippable() {
        Staff staff1 = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));  
        Character character = createCharacter(); 

        character.equipItem(staff1);

        Staff staff2 = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));   

        assertFalse(staff2.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testGetModifiedDamage() {
        // 3 points of damage
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));  
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int baseDamage = 2;
        assertEquals(3, staff.getModifiedDamage(slug, baseDamage));
    }

    @Test
    public void testGetModifiedEnemyDamage() {
        // doesn't affect enemy damage
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        int baseDamage = 10;
        assertEquals(baseDamage, staff.getModifiedEnemyDamage(baseDamage));
    }

    @Test
    public void testGetModifiedCriticalChance() {
        // doesn't modify critical chance
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        double baseCriticalChance = 0.2;

        assertEquals(baseCriticalChance, staff.getModifiedCriticalChance(baseCriticalChance));
    }

    @Test
    public void attackSlug() {
        // changing enemy to an allied soldier
    } 
}
