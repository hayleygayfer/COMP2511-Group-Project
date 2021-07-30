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
import unsw.loopmania.enemies.ElanMuske;

public class ElanMuskeTest {
    @Test
    public void testConstructor() {
        List<Pair<Integer, Integer>> path = TestHelper.createPath();
        BasicEnemy enemy = new ElanMuske(new PathPosition(0, path));
        assertTrue(enemy instanceof ElanMuske);
        assertTrue(enemy instanceof BasicEnemy);
        assertTrue(enemy instanceof MovingEntity);
        assertTrue(enemy instanceof Entity);

        // check stats
        assertEquals(10, enemy.getDamage());
        assertEquals(2, enemy.getBattleRadius());
        assertEquals(120, enemy.getHealth());
    }

    @Test
    public void testMoveInitial() {
        List<Pair<Integer, Integer>> path = TestHelper.createSquarePath(5, 0);
        ElanMuske elanMuske = new ElanMuske(new PathPosition(2, path));
        PathPosition position = elanMuske.getPosition();
        int initialX = position.getXValue();
        int initialY = position.getYValue();

        elanMuske.move(0);
        
        // elanMuske must have moved either left or right
        assertEquals(initialY, position.getYValue());
        assertTrue(Math.abs(initialX - position.getXValue()) == 1);
    }

    @Test
    public void testMoveSecond() {
        List<Pair<Integer, Integer>> path = TestHelper.createSquarePath(5, 0);
        ElanMuske elanMuske = new ElanMuske(new PathPosition(2, path));
        PathPosition position = elanMuske.getPosition();
        int initialX = position.getXValue();
        int initialY = position.getYValue();

        elanMuske.move(1);
        
        // on the second tick, elanMuske should still have moved either left or right
        assertEquals(initialY, position.getYValue());
        assertTrue(Math.abs(initialX - position.getXValue()) == 1);
    }
}
