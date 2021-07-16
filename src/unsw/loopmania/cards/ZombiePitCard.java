package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.Card;
import unsw.loopmania.Building;
import java.util.List;
import org.javatuples.Pair;
import java.io.File;

public class ZombiePitCard extends Card {

    public ZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public boolean isValidPosition(SimpleIntegerProperty x, SimpleIntegerProperty y, List<Pair<Integer, Integer>> path) {
        // TODO Auto-generated method stub
        return false;
    }

    public Building generateBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // TODO Auto-generated method stub
        return null;
    }   

    public Image render() {
        return new Image((new File("src/images/zombie_pit_card.png")).toURI().toString());
    }
}
