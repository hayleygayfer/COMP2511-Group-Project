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

import unsw.loopmania.generateItems.SwordGenerateItem;
import unsw.loopmania.generateItems.StakeGenerateItem;
import unsw.loopmania.generateItems.ShieldGenerateItem;
import unsw.loopmania.generateItems.StaffGenerateItem;
import unsw.loopmania.generateItems.ArmourGenerateItem;
import unsw.loopmania.generateItems.HelmetGenerateItem;

import unsw.loopmania.GenerateItem;
import unsw.loopmania.BasicEnemy;

import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Zombie;

public class EnemyDropTest {
    @Test
    public void testVampireDrop() {
        List<Pair<Integer, Integer>> path = TestHelper.createPath();
        LoopManiaWorld world = TestHelper.createWorld(TestHelper.createPath());
        Character character = world.getCharacter();

        Vampire testVampire = new Vampire(new PathPosition(0, path));
    }
}
