package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Card;
import java.util.List;
import org.javatuples.Pair;
import javafx.scene.image.Image;
import java.io.File;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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

    public Building generateBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // TODO Auto-generated method stub
        return null;
    }   

    public Image render() {
        return new Image((new File("src/images/vampire_castle_card.png")).toURI().toString());
    }
     
}
