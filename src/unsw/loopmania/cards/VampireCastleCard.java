package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Card;
import java.util.List;
import org.javatuples.Pair;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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
     
}
