package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.Building;
import unsw.loopmania.EnemyPositionObserver;
import javafx.scene.image.Image;
import java.io.File;
import java.util.List;

import org.javatuples.Pair;

public class TowerBuilding extends Building implements EnemyPositionObserver {
    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    /**
     * If enemy is on an adjacent tile, deduct health by 2
     * @param enemy
     * @pre enemy != NULL
     */
    public void encounter(BasicEnemy enemy) {
        if (this.shouldExist().get()) {
            List<Pair<Integer, Integer>> adjacentSquares = getAdjacentSquares(getX(), getY());
            if (adjacentSquares.contains(Pair.with(enemy.getX(), enemy.getY()))) {
                enemy.deductHealth(2);
            }
        }
    }

    public Image render() {
        return new Image((new File("src/images/tower.png")).toURI().toString());
    }
}
