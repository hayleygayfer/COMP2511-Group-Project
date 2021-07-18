package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;
import unsw.loopmania.buildings.TowerBuilding;
import unsw.loopmania.Building;
import java.util.List;
import org.javatuples.Pair;
import javafx.scene.image.Image;
import java.io.File;

public class TowerCard extends Card {

    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Position is valid if it is adjacent to a path tile and is not a path tile itself
     * @param x x coordinate of position
     * @param y y coordinate of position
     * @return whether the card can be placed at the given position
     */
    public boolean isValidPosition(SimpleIntegerProperty x, SimpleIntegerProperty y, List<Pair<Integer, Integer>> path) {
        Pair<Integer, Integer> position = new Pair<Integer, Integer>(x.get(), y.get());
        // can't place on a path tile
        if (path.contains(position)) {
            return false;
        }
        // check that there is an adjacent path tile
        List<Pair<Integer, Integer>> adjacentPathTiles = getAdjacentSquares(x.get(), y.get());
        adjacentPathTiles.removeIf(p -> !path.contains(p));
        return adjacentPathTiles.size() > 0;
    }

    /**
     * Generates a tower from this card at the supplied position
     * @param x x coordinate of spawn position
     * @param y y coordinate of spawn position
     * @return the generated building
     * @pre the supplied position is a valid spawn position
     */
    public Building generateBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new TowerBuilding(x, y);
    }   

    public Image render() {
        return new Image((new File("src/images/tower_card.png")).toURI().toString());
    }
}
