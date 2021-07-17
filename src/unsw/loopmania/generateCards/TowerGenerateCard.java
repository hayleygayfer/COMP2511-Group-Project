package unsw.loopmania.generateCards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.Card;
import unsw.loopmania.cards.TowerCard;

public class TowerGenerateCard extends GenerateCard {
    public TowerGenerateCard() {
        super();
    }

    public Card createCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new TowerCard(x, y);
    }
}