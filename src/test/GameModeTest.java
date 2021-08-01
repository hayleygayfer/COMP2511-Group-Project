package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import unsw.loopmania.GameMode;
import unsw.loopmania.gameModes.ConfusingMode;
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
        assertTrue(c instanceof GameMode);
    }
    
}
