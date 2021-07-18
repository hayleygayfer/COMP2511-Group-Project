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
import unsw.loopmania.HerosCastleMenu;
import unsw.loopmania.PathPosition;
import unsw.loopmania.StaticEntity;
import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Battle;
import unsw.loopmania.EquippableItem;
import unsw.loopmania.Item;
import unsw.loopmania.items.Sword;
import unsw.loopmania.items.Stake;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Goals.CycleLeaf;
import unsw.loopmania.Goals.XpLeaf;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.Goals.GoldLeaf;
import unsw.loopmania.Goals.GoalAND;
import unsw.loopmania.Entity;
import unsw.loopmania.Card;
import unsw.loopmania.cards.BarracksCard;
import unsw.loopmania.cards.CampfireCard;
import unsw.loopmania.cards.VillageCard;
import unsw.loopmania.cards.TowerCard;
import unsw.loopmania.cards.VampireCastleCard;
import unsw.loopmania.cards.ZombiePitCard;

import org.javatuples.Triplet;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Random;

public class LoopManiaWorldTest {
    private SimpleIntegerProperty x = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty y = new SimpleIntegerProperty(0);
    List<Pair<Integer, Integer>> path = TestHelper.createPath();

    @Test
    public void testConstructor() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        assertTrue(world instanceof LoopManiaWorld);
    }

    @Test
    public void testGetHerosCastleMenu() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        assertTrue(world.getHerosCastleMenu() instanceof HerosCastleMenu);
    }

    @Test
    public void testGetWidthHeight() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        assertEquals(6, world.getWidth());
        assertEquals(6, world.getHeight());
    }

    @Test
    public void testSetandGetGameGoal() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Goal goal1 = new GoldLeaf(20);
        Goal goal2 = new CycleLeaf(20);
        Goal goal3 = new XpLeaf(20);

        JSONObject goal1JSON = new JSONObject(goal1);
        JSONObject goal2JSON = new JSONObject(goal2);
        JSONObject goal3JSON = new JSONObject(goal3);

        JSONArray goals = new JSONArray();
        goals.put(goal1JSON);
        goals.put(goal2JSON);

        GoalAND cyclesAndGold = new GoalAND(goals);

        JSONObject cyclesAndGoldJSON = new JSONObject(cyclesAndGold);
        JSONArray allGoalsJSON = new JSONArray();

        goals.put(cyclesAndGoldJSON);
        goals.put(goal3JSON);

        GoalAND allGoals = new GoalAND(allGoalsJSON);

        world.setGameGoal(allGoals);

        assertEquals(world.getGameGoal(), allGoals);
    }

    @Test
    public void testSetandGetCharacter() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        PathPosition newPosition = new PathPosition(0, path);

        Character newCharacter = new Character(newPosition);

        world.setCharacter(newCharacter);
        assertEquals(newCharacter, world.getCharacter());
    }

    @Test
    public void testAddEntity() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Entity newEntity = new Sword(x, y);
        world.addEntity(newEntity);
        // no getter to test this atm
    }

    @Test
    public void testSpawnRandomEnemies() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        List<BasicEnemy> spawnedEnemies = world.possiblySpawnEnemies();
        assertTrue(spawnedEnemies.size() == 1);
    }

    @Test
    public void testSpawnBuildingEnemies() {
        LoopManiaWorld world = TestHelper.createWorld(path);

    }

    @Test
    public void testSpawnGold() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        List<Gold> gold = world.possiblySpawnGold();
        assertTrue(gold.size() == 0);
        List<Gold> gold2 = world.possiblySpawnGold();
        assertTrue(gold2.size() == 0);
        List<Gold> gold3 = world.possiblySpawnGold();
        assertTrue(gold3.size() == 0);
    }

    @Test
    public void testSellItem() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        HerosCastleMenu shop = world.getHerosCastleMenu();
        Character character = world.getCharacter();

        Item item = new Sword(x, y);

        character.addItemToInventory(item);

        world.sellItem(item);

        assertEquals(10, character.getGold());
        assertFalse(character.getInventory().contains(item));
    }

    @Test 
    public void testRemoveItem() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        Item item = new Sword(x, y);

        character.addItemToInventory(item);

        world.removeItemWhenUsed(item);
        assertFalse(character.getInventory().contains(item));
    }

    @Test
    public void testGetEnemyItemDrops() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        PathPosition newPosition = new PathPosition(0, path);
        BasicEnemy slug = new Slug(newPosition);
        BasicEnemy vampire = new Vampire(newPosition);
        BasicEnemy zombie = new Zombie(newPosition);

        List<Item> slugDrops = world.defeatedEnemyItemDrops(slug);
        List<Item> vampireDrops = world.defeatedEnemyItemDrops(vampire);
        List<Item> zombieDrops = world.defeatedEnemyItemDrops(zombie);

        assertEquals(0, slugDrops.size());
        assertEquals(1, vampireDrops.size());
        assertEquals(0, zombieDrops.size());

        assertTrue(world.getCharacter().getInventory().containsAll(slugDrops));
        assertTrue(world.getCharacter().getInventory().containsAll(vampireDrops));
        assertTrue(world.getCharacter().getInventory().containsAll(zombieDrops));
    }

    @Test
    public void testGetGoldAndXp() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        PathPosition newPosition = new PathPosition(0, path);
        BasicEnemy slug = new Slug(newPosition);
        BasicEnemy vampire = new Vampire(newPosition);
        BasicEnemy zombie = new Zombie(newPosition);

        world.getGoldAndXpDrops(slug);
        assertEquals(5, world.getCharacter().getXpProperty().get());
        assertEquals(1, world.getCharacter().getGold());

        world.getGoldAndXpDrops(vampire);
        assertEquals(25, world.getCharacter().getXpProperty().get());
        assertEquals(6, world.getCharacter().getGold());

        world.getGoldAndXpDrops(zombie);
        assertEquals(35, world.getCharacter().getXpProperty().get());
        assertEquals(8, world.getCharacter().getGold());
    }

    @Test 
    public void testEnemyCardDrops() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        PathPosition newPosition = new PathPosition(0, path);
        BasicEnemy slug = new Slug(newPosition);
        BasicEnemy vampire = new Vampire(newPosition);
        BasicEnemy zombie = new Zombie(newPosition);

        List<Card> slugCards = world.defeatedEnemyCardDrops(slug);
        List<Card> vampireCards = world.defeatedEnemyCardDrops(vampire);
        List<Card> zombieCards = world.defeatedEnemyCardDrops(zombie);

        assertEquals(0, slugCards.size());
        assertTrue(vampireCards.get(0) instanceof CampfireCard);
        assertEquals(0, zombieCards.size());

        List<Card> slugCards2 = world.defeatedEnemyCardDrops(slug);
        List<Card> vampireCards2 = world.defeatedEnemyCardDrops(vampire);
        List<Card> zombieCards2 = world.defeatedEnemyCardDrops(zombie);

        assertTrue(slugCards2.get(0) instanceof BarracksCard);
        assertTrue(vampireCards2.get(0) instanceof VillageCard);
        assertTrue(zombieCards2.get(0) instanceof TowerCard);
    }

    @Test
    public void testRunBattles() {
        LoopManiaWorld world = TestHelper.createWorld(path);

        List<BasicEnemy> spawnedEnemies = world.possiblySpawnEnemies();
        for (BasicEnemy enemy : spawnedEnemies) {
            PathPosition position = enemy.getPosition();
            Character newCharacter = new Character(position);
            world.setCharacter(newCharacter);
        }
        List<BasicEnemy> defeatedEnemies = world.runBattles();
        assertTrue(defeatedEnemies.contains(spawnedEnemies.get(0)));
    }

    @Test
    public void testSetCurrentBattle() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();
        PathPosition newPosition = new PathPosition(0, path);

        List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
        BasicEnemy slug = new Slug(newPosition);
        enemies.add(slug);

        Battle newBattle = new Battle(character, enemies);
        world.setCurrentBattle(newBattle);
        
        assertEquals(world.getCurrentBattle(), newBattle);
    }

    @Test
    public void testRemoveItemByCoordinates() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        Item sword = new Sword(x, y);
        character.addItemToInventory(sword);

        world.removeUnequippedInventoryItemByCoordinates(x.get(), y.get());
        assertFalse(character.getInventory().contains(sword));
    }

    @Test
    public void testEquipItemByCoordinates() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        Item sword = new Sword(x, y);
        character.addItemToInventory(sword);

        world.equipInventoryItemByCoordinates(x.get(), y.get());
        assertTrue(character.getEquippedItems().contains(sword));
        assertFalse(character.getInventory().contains(sword));
    }

    @Test
    public void testRunTickMoves() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        PathPosition expectedPosition = new PathPosition(1, path);

        world.runTickMoves();
        assertEquals(expectedPosition.getXValue(), character.getPosition().getXValue());
        assertEquals(expectedPosition.getYValue(), character.getPosition().getYValue());
    }

    @Test
    public void testFirstAvailableSlotForItem() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        Item sword = new Sword(x, y);
        Item stake = new Stake(new SimpleIntegerProperty(x.get() + 1), y);

        Pair<Integer, Integer> coords = world.getFirstAvailableSlotForItem();

        assertEquals(0, coords.getValue0());
        assertEquals(0, coords.getValue1());

        character.addItemToInventory(sword);

        Pair<Integer, Integer> coords2 = world.getFirstAvailableSlotForItem();

        assertEquals(1, coords2.getValue0());
        assertEquals(0, coords2.getValue1());

        character.addItemToInventory(stake);

        Pair<Integer, Integer> coords3 = world.getFirstAvailableSlotForItem();

        assertEquals(2, coords3.getValue0());
        assertEquals(0, coords3.getValue1());
    }

    @Test
    public void testHerosCastleEncounter() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        Character character = world.getCharacter();

        PathPosition initialPosition = character.getPosition();
        assertTrue(world.characterAtHerosCastle());

        while (character.getPosition().getXValue() != initialPosition.getXValue() ||
        character.getPosition().getYValue() != initialPosition.getYValue()) {
            world.runTickMoves();
        }

        assertTrue(world.characterAtHerosCastle());

        world.encounter(character);
        assertEquals(0, world.getGameCycle());
    }

    @Test
    public void testGameCycle() {
        LoopManiaWorld world = TestHelper.createWorld(path);
        assertEquals(0, world.getGameCycle());

        for (int i = 0; i < 5; i++) {
            world.iterateGamecycle();
        }
        assertEquals(5, world.getGameCycle());

        assertTrue(world.getGameCycleProperty() instanceof SimpleIntegerProperty);
    }


}
