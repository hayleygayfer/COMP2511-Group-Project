package test.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;


import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import test.TestHelper;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Entity;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Doggie;

public class DoggieTest {
    @Test
    public void testConstructor() {
        List<Pair<Integer, Integer>> path = TestHelper.createPath();
        BasicEnemy enemy = new Doggie(new PathPosition(0, path));
        assertTrue(enemy instanceof Doggie);
        assertTrue(enemy instanceof BasicEnemy);
        assertTrue(enemy instanceof MovingEntity);
        assertTrue(enemy instanceof Entity);

        // check stats
        assertEquals(5, enemy.getDamage());
        assertEquals(2, enemy.getBattleRadius());
        assertEquals(60, enemy.getHealth());
    }

    @Test
    public void testMoveInitial() {
        List<Pair<Integer, Integer>> path = TestHelper.createSquarePath(5, 0);
        Doggie Doggie = new Doggie(new PathPosition(2, path));
        PathPosition position = Doggie.getPosition();
        int initialX = position.getXValue();
        int initialY = position.getYValue();

        Doggie.move(0);
        
        // Doggie must have moved either left or right
        assertEquals(initialY, position.getYValue());
        assertTrue(Math.abs(initialX - position.getXValue()) == 1);
    }

    @Test
    public void testMoveSecond() {
        List<Pair<Integer, Integer>> path = TestHelper.createSquarePath(5, 0);
        Doggie Doggie = new Doggie(new PathPosition(2, path));
        PathPosition position = Doggie.getPosition();
        int initialX = position.getXValue();
        int initialY = position.getYValue();

        Doggie.move(1);
        
        // on the second tick, Doggie should still have moved either left or right
        assertEquals(initialY, position.getYValue());
        assertTrue(Math.abs(initialX - position.getXValue()) == 1);
    }
}
