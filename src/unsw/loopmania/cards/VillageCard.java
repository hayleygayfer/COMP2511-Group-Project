package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.Building;
import java.util.List;
import org.javatuples.Pair;
import javafx.scene.image.Image;
import java.io.File;

public class VillageCard extends Card {

    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Position is valid if it is a path tile
     * @param x x coordinate of position
     * @param y y coordinate of position
     * @return whether the card can be placed at the given position
     */
    public boolean isValidPosition(SimpleIntegerProperty x, SimpleIntegerProperty y, List<Pair<Integer, Integer>> path) {
        return path.contains(Pair.with(x.get(), y.get()));
    }

    /**
     * Generates a village building from this card at the supplied position
     * @param x x coordinate of spawn position
     * @param y y coordinate of spawn position
     * @return the generated building
     * @pre the supplied position is a valid spawn position
     */
    public Building generateBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new VillageBuilding(x, y);
    }   

    public Image render() {
        return new Image((new File("src/images/village_card.png")).toURI().toString());
    }
}
