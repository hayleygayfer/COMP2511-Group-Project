package test;

import java.util.List;

import org.javatuples.Pair;

import java.util.List;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Cylinder;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import unsw.loopmania.Goals.Goal;
import unsw.loopmania.Goals.CycleLeaf;



public class GoalsTest {
    List<Pair<Integer, Integer>> path = TestHelper.createPath();

    @Test 
    public void cycleLeafConstructorTest() {
        CycleLeaf c = new CycleLeaf(3);
        assertTrue(c instanceof Goal);
    }
    
}

    
