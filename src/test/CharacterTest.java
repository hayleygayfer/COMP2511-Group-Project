package test;

import java.util.List;


import java.util.ArrayList;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import unsw.loopmania.Character;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.Item;
import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.items.Armour;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Sword;

public class CharacterTest {
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
        PathPosition position = new PathPosition(0, createPath());
        Character character = new Character(position);

        assertTrue(character instanceof Character);
        assertTrue(character instanceof MovingEntity);
        assertTrue(character instanceof Entity);
    }
    
    @Test
    public void testHealth() {
        Character character = createCharacter();
        assertEquals(50, character.getHealth());
        assertEquals(new SimpleIntegerProperty(50), character.health());
    }

    @Test
    public void testLoseHealth() {
        Character character = createCharacter();

        character.loseHealth(20);
        assertEquals(30, character.getHealth());

        character.loseHealth(25);
        assertEquals(5, character.getHealth());

        character.loseHealth(10);
        assertEquals(-5, character.getHealth());
    }

    @Test
    public void testIsAlive() {
        Character character = createCharacter();

        assertTrue(character.isAlive());

        character.loseHealth(30);
        assertTrue(character.isAlive());
    }

    @Test
    public void testIsDead() {
        Character character = createCharacter();

        character.loseHealth(50);
        assertFalse(character.isAlive());
    }

    @Test
    public void testGetDamage() {
        Character character = createCharacter();

        assertEquals(2, character.getDamage());
    }

    @Test
    public void testAttack() {
        Character character = createCharacter();
        Slug enemy = new Slug(new PathPosition(0, createPath()));

        character.attack(enemy, 5);

        assertEquals(5, enemy.getHealth());
    }

    @Test
    public void getInventory() {
        Character character = createCharacter();

        List<Item> expectedInventory = new ArrayList<>();

        assertTrue(expectedInventory.equals(character.getInventory()));
    }

    @Test
    public void testAddItemToInventory() {
        Character character = createCharacter();
        List<Item> expectedInventory = new ArrayList<>();

        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        expectedInventory.add(sword);
        character.addItemToInventory(sword);
        assertTrue(expectedInventory.equals(character.getInventory()));

        HealthPotion potion = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        expectedInventory.add(potion);
        character.addItemToInventory(potion);
        assertTrue(expectedInventory.equals(character.getInventory()));
    }

    @Test
    public void testRemoveItemFromInventory() {
        Character character = createCharacter();
        List<Item> expectedInventory = new ArrayList<>();

        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion potion = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        character.addItemToInventory(sword);
        character.addItemToInventory(potion);
        character.removeItemFromInventory(sword);
        expectedInventory.add(potion);

        assertTrue(expectedInventory.equals(character.getInventory()));
    }

    @Test
    public void testGetEquippedItems() {
        Character character = createCharacter();
        List<Item> expectedEquippedItems = new ArrayList<>();

        assertTrue(expectedEquippedItems.equals(character.getEquippedItems()));
    }

    @Test
    public void testEquipItem() {
        Character character = createCharacter();
        List<Item> expectedEquippedItems = new ArrayList<>();

        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        character.equipItem(sword);
        expectedEquippedItems.add(sword);
        assertTrue(expectedEquippedItems.equals(character.getEquippedItems()));

        character.equipItem(armour);
        expectedEquippedItems.add(armour);
        assertTrue(expectedEquippedItems.equals(character.getEquippedItems()));
    }

    @Test
    public void testUnequipItem() {
        Character character = createCharacter();
        List<Item> expectedEquippedItems = new ArrayList<>();

        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Armour armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        character.equipItem(sword);
        character.equipItem(armour);

        character.unequipItem(sword);
        expectedEquippedItems.add(armour);
        expectedEquippedItems.equals(character.getEquippedItems());

        character.unequipItem(armour);
        expectedEquippedItems.remove(armour);
        expectedEquippedItems.equals(character.getEquippedItems());
    }

    @Test
    public void testUseItem() {
        Character character = createCharacter();
        List<Item> expectedInventory = new ArrayList<>();

        HealthPotion potion = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        character.addItemToInventory(potion);

        character.useItem(potion);

        // item is no longer in the inventory
        assertTrue(expectedInventory.equals(character.getInventory()));
    }
}
