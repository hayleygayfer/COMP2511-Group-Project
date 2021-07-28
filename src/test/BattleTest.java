package test;

import java.util.List;

import java.util.ArrayList;
import org.javatuples.Pair;
import org.javatuples.Quartet;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Battle;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.items.Sword;
import unsw.loopmania.items.Stake;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;


public class BattleTest {
    private SimpleIntegerProperty x = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty y = new SimpleIntegerProperty(0);
    List<Pair<Integer, Integer>> path = TestHelper.createPath();

    @Test
    public void testOneSlugNoItems() {
        Character character = TestHelper.createCharacter(path);
        PathPosition newPosition = new PathPosition(0, path);

        BasicEnemy slug = new Slug(newPosition);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        enemies.add(slug);

        Battle newBattle = new Battle(character, enemies);

        newBattle.runBattle();
        assert(newBattle.wonBattle());
    }

    @Test
    public void testOneSlugWithSword() {
        Character character = TestHelper.createCharacter(path);
        PathPosition newPosition = new PathPosition(0, path);

        BasicEnemy slug = new Slug(newPosition);

        EquippableItem sword = new Sword(x, y);

        character.equipItem(sword);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        enemies.add(slug);

        Battle newBattle = new Battle(character, enemies);

        List<Quartet<Double, Double, BasicEnemy, Integer>> frames = newBattle.runBattle();

        assertEquals(frames.size(), 2);

        assertTrue(newBattle.wonBattle());
    }

    @Test
    public void testOneSlugWithLowHealth() {
        Character character = TestHelper.createCharacter(path);
        PathPosition newPosition = new PathPosition(0, path);

        BasicEnemy slug = new Slug(newPosition);

        character.setCurrentHealth(4);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        enemies.add(slug);

        Battle newBattle = new Battle(character, enemies);

        List<Quartet<Double, Double, BasicEnemy, Integer>> frames = newBattle.runBattle();

        assertEquals(frames.size(), 5);
        assertFalse(newBattle.wonBattle());
    }

    @Test
    public void testOneVampireWithStake() {
        Character character = TestHelper.createCharacter(path);
        PathPosition newPosition = new PathPosition(0, path);

        BasicEnemy vampire = new Vampire(newPosition);

        EquippableItem stake = new Stake(x, y);

        character.equipItem(stake);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        enemies.add(vampire);

        Battle newBattle = new Battle(character, enemies);

        List<Quartet<Double, Double, BasicEnemy, Integer>> frames = newBattle.runBattle();

        assertEquals(frames.size(), 2);
        assertTrue(newBattle.wonBattle());
    }

    @Test
    public void testOneVampireNoStake() {
        Character character = TestHelper.createCharacter(path);
        PathPosition newPosition = new PathPosition(0, path);

        BasicEnemy vampire = new Vampire(newPosition);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        enemies.add(vampire);

        Battle newBattle = new Battle(character, enemies);

        List<Quartet<Double, Double, BasicEnemy, Integer>> frames = newBattle.runBattle();

        assertEquals(frames.size(), 16);
        assertTrue(newBattle.wonBattle());
    }

    @Test
    public void testOneVampireWithLowHealth() {
        Character character = TestHelper.createCharacter(path);
        PathPosition newPosition = new PathPosition(0, path);

        BasicEnemy vampire = new Vampire(newPosition);

        character.setCurrentHealth(20);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        enemies.add(vampire);

        Battle newBattle = new Battle(character, enemies);

        List<Quartet<Double, Double, BasicEnemy, Integer>> frames = newBattle.runBattle();

        assertEquals(frames.size(), 8);
        assertFalse(newBattle.wonBattle());
    }

    @Test
    public void testOneVampireOneSlug() {
        Character character = TestHelper.createCharacter(path);
        PathPosition newPosition = new PathPosition(0, path);

        BasicEnemy vampire = new Vampire(newPosition);
        BasicEnemy slug = new Slug(newPosition);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        enemies.add(vampire);
        enemies.add(slug);

        Battle newBattle = new Battle(character, enemies);

        List<Quartet<Double, Double, BasicEnemy, Integer>> frames = newBattle.runBattle();

        assertEquals(frames.size(), 20);
        assertTrue(newBattle.wonBattle());
    }
}
