package test.items;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import unsw.loopmania.items.Stake;
import unsw.loopmania.items.Sword;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Entity;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;

public class StakeTest {
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
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(stake instanceof Stake);
        assertTrue(stake instanceof EquippableItem);
        assertTrue(stake instanceof Item);
        assertTrue(stake instanceof StaticEntity);
        assertTrue(stake instanceof Entity);
    }

    @Test
    public void testPrice() {
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 

        // stake costs 10 gold
        assertEquals(10, stake.getPrice());
    }

    @Test
    public void testIsEquippable() {
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Character character = createCharacter();

        assertTrue(stake.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testIsEquippableWithOtherAttackItems() {
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Character character = createCharacter(); 

        character.equipItem(sword);

        // can't equip stake item if another attack item is equipped
        assertFalse(stake.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testDuplicateIsEquippable() {
        Stake stake1 = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));  
        Character character = createCharacter(); 

        character.equipItem(stake1);

        Stake stake2 = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));   

        // can't equip stake if another stake is equipped
        assertFalse(stake2.isEquippable(character.getEquippedItems()));
    }

    @Test
    public void testGetModifiedDamageRegularEnemy() {
        // damage on slugs and zombies is 6
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        int baseDamage = 2;

        Slug slug = new Slug(new PathPosition(0, createPath()));
        Zombie zombie = new Zombie(new PathPosition(0, createPath()));

        assertEquals(6, stake.getModifiedDamage(slug, baseDamage));
        assertEquals(6, stake.getModifiedDamage(zombie, baseDamage));
    }

    @Test
    public void testGetModifiedDamageVampire() {
        // 20 points damage on vampires
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Vampire vampire = new Vampire(new PathPosition(0, createPath()));

        int baseDamage = 2;

        assertEquals(20, stake.getModifiedDamage(vampire, baseDamage));
    }

    @Test
    public void testGetModifiedEnemyDamage() {
        // won't affect enemy damage
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        int baseDamage = 10;
        assertEquals(baseDamage, stake.getModifiedEnemyDamage(baseDamage));
    }

    @Test
    public void testGetModifiedCriticalChance() {
        // doesn't modify critical chance
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        double baseCriticalChance = 0.2;

        assertEquals(baseCriticalChance, stake.getModifiedCriticalChance(baseCriticalChance));
    }

    @Test
    public void attackSlug() {
        // decrease slug health by 3
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Slug slug = new Slug(new PathPosition(0, createPath()));

        int initialHealth = slug.getHealth();

        stake.attack(slug, 3);

        assertEquals(initialHealth - 3, slug.getHealth());
    } 

    @Test
    public void attackVampire() {
        // decrease vampire health by 20
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)); 
        Vampire vampire = new Vampire(new PathPosition(0, createPath()));

        int initialHealth = vampire.getHealth();

        stake.attack(vampire, 20);

        assertEquals(initialHealth - 20, vampire.getHealth()); 
    }
}
