package test;

import java.util.List;


import java.util.ArrayList;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import unsw.loopmania.Character;
import unsw.loopmania.Gold;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.HerosCastleMenu;
import unsw.loopmania.Item;
import unsw.loopmania.items.Sword;
import unsw.loopmania.items.HealthPotion;

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
    public void testAddItemsToMenu() {
        HerosCastleMenu menu = new HerosCastleMenu();
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    }

    @Test 
    public void testPurchaseItem() {
        Character character = createCharacter();

        HerosCastleMenu menu = new HerosCastleMenu();
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    }

    @Test
    public void testNotEnoughGoldForPurchase() {
        Character character = createCharacter();

        HerosCastleMenu menu = new HerosCastleMenu();
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    }
}
