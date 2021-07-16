package unsw.loopmania.buildings;

import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.PathPosition;
import unsw.loopmania.SpawnEnemyStrategy;
import unsw.loopmania.enemies.Vampire;
import javafx.scene.image.Image;
import java.io.File;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building implements SpawnEnemyStrategy {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(y, y);
    }

    /**
     * Spawns Vampire on an adjacent path tile
     * @pre character is currently at hero's castle 
     */
    public Vampire possiblySpawnEnemy(List<Pair<Integer, Integer>> orderedPath, int gameCycle) {
        if (gameCycle % 5 == 0) {
            List<Pair<Integer, Integer>> adjacentPathTiles = getAdjacentSquares(getX(), getY());
            adjacentPathTiles.removeIf(p -> !orderedPath.contains(p));
            // remove hero's castle tile
            adjacentPathTiles.removeIf(p -> orderedPath.indexOf(p) == 0);

            Random random = new Random();
            Pair<Integer, Integer> spawnTile = adjacentPathTiles.get(random.nextInt(adjacentPathTiles.size()));

            return new Vampire(new PathPosition(orderedPath.indexOf(spawnTile), orderedPath));
        }
        return null;
    }

    public Image render() {
        return new Image((new File("src/images/vampire_castle_building_transparent.png")).toURI().toString());
    }
}
