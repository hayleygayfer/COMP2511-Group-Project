package unsw.loopmania.generateCards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.Card;
import unsw.loopmania.cards.BarracksCard;

public class BarracksGenerateCard extends GenerateCard {
    public BarracksGenerateCard() {
        super();
    }

    public Card createCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new BarracksCard(x, y);
    }
}
