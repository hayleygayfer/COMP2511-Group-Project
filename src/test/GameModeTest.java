package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import test.generateItems.ShieldGenerateItemTest;
import unsw.loopmania.GameMode;
import unsw.loopmania.gameModes.BerserkerMode;
import unsw.loopmania.gameModes.ConfusingMode;
import unsw.loopmania.gameModes.SurvivalMode;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;
import unsw.loopmania.generateItems.ShieldGenerateItem;
import unsw.loopmania.generateItems.SwordGenerateItem;
import unsw.loopmania.Character;

public class GameModeTest {

    @Test
    public void confusingModeConstructorTest() {
        ConfusingMode c = new ConfusingMode();
        assertTrue(c instanceof GameMode);
    }

    @Test
    public void confusingModeLimitPurchaseTest() {
        ConfusingMode c = new ConfusingMode();
        Character character = TestHelper.createCharacter(TestHelper.createPath());
        SwordGenerateItem menuSword = new SwordGenerateItem();
        assertTrue( c.limitPurchase(character, menuSword));
    }

    @Test
    public void berserkerModeConstructorTest() {
        BerserkerMode c = new BerserkerMode();
        assertTrue(c instanceof GameMode);
    }

    @Test
    public void berserkerModeLimitPurchaseShieldTest() {
        BerserkerMode c = new BerserkerMode();
        Character character = TestHelper.createCharacter(TestHelper.createPath());
        ShieldGenerateItem item = new ShieldGenerateItem();
        character.addPurchase(item);
        assertFalse( c.limitPurchase(character, item));
    }

    @Test
    public void berserkerModeLimitPurchaseTest() {
        BerserkerMode c = new BerserkerMode();
        Character character = TestHelper.createCharacter(TestHelper.createPath());
        SwordGenerateItem item = new SwordGenerateItem();
        character.addPurchase(item);
        assertTrue( c.limitPurchase(character, item));
    }

    @Test
    public void survivalModeConstructorTest() {
        SurvivalMode c = new SurvivalMode();
        assertTrue(c instanceof GameMode);
    }

    @Test
    public void survivalModeLimitPurchaseHealthPotionTest() {
        SurvivalMode c = new SurvivalMode();
        Character character = TestHelper.createCharacter(TestHelper.createPath());
        HealthPotionGenerateItem item = new HealthPotionGenerateItem();
        character.addPurchase(item);
        assertFalse( c.limitPurchase(character, item));
    }

    @Test
    public void survivalModeLimitPurchaseTest() {
        SurvivalMode c = new SurvivalMode();
        Character character = TestHelper.createCharacter(TestHelper.createPath());
        SwordGenerateItem item = new SwordGenerateItem();
        character.addPurchase(item);
        assertTrue( c.limitPurchase(character, item));
    }
    
}
