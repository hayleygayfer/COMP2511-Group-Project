package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;
import unsw.loopmania.Building;

public class TrapCard extends Card {

    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public boolean isValidPosition(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // TODO Auto-generated method stub
        return false;
    }

    public Building generateBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // TODO Auto-generated method stub
        return null;
    }   
}
