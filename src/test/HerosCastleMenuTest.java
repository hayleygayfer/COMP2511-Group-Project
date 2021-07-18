package test;

import java.util.List;


import java.util.ArrayList;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import unsw.loopmania.Character;
import unsw.loopmania.Gold;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.generateItems.StakeGenerateItem;
import unsw.loopmania.generateItems.SwordGenerateItem;
import unsw.loopmania.generateItems.ShieldGenerateItem;
import unsw.loopmania.generateItems.StaffGenerateItem;
import unsw.loopmania.generateItems.ArmourGenerateItem;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;
import unsw.loopmania.generateItems.TheOneRingGenerateItem;
import unsw.loopmania.generateItems.HelmetGenerateItem;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.HerosCastleMenu;
import unsw.loopmania.Item;
import unsw.loopmania.items.Sword;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.GenerateItem;

public class HerosCastleMenuTest {
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

    public LoopManiaWorld createWorld() {
        return new LoopManiaWorld(6, 6, createPath());
    }

    @Test
    public void testConstructor() {
        HerosCastleMenu menu = new HerosCastleMenu();

        assertTrue(menu instanceof HerosCastleMenu);
    }

    @Test 
    public void testPurchaseItem() {
        Character character = createCharacter();

        HerosCastleMenu menu = new HerosCastleMenu();

        character.addGold(10);

        SwordGenerateItem menuSword = new SwordGenerateItem();
    
        Item purchasedItem = menu.purchaseItem(character, menuSword, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(character.getInventory().contains(purchasedItem));
        assertEquals(0, character.getGold().get());
    }

    @Test
    public void testNotEnoughGoldForPurchase() {
        Character character = createCharacter();

        HerosCastleMenu menu = new HerosCastleMenu();

        character.addGold(5);
        
        SwordGenerateItem menuSword = new SwordGenerateItem();
    
        Item purchasedItem = menu.purchaseItem(character, menuSword, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertFalse(character.getInventory().contains(purchasedItem));
        assertEquals(5, character.getGold().get());
    }

    @Test
    public void testSellItem() {
        Character character = createCharacter();

        HerosCastleMenu menu = new HerosCastleMenu();

        character.addGold(10);
        
        SwordGenerateItem menuSword = new SwordGenerateItem();
    
        Item purchasedItem = menu.purchaseItem(character, menuSword, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(character.getInventory().contains(purchasedItem));
        assertEquals(0, character.getGold().get());

        menu.sellItem(character, purchasedItem);

        assertFalse(character.getInventory().contains(purchasedItem));
        assertEquals(10, character.getGold().get());
    }

    @Test 
    public void testAddShopItem() {
        HerosCastleMenu menu = new HerosCastleMenu();
        
        TheOneRingGenerateItem menuTheOneRing = new TheOneRingGenerateItem();

        menu.addItem(menuTheOneRing);
        assertTrue(menu.hasItem(menuTheOneRing));
    }
}
