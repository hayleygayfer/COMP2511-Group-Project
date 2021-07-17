package unsw.loopmania.generateCards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.Card;
import unsw.loopmania.cards.CampfireCard;

public class CampfireGenerateCard extends GenerateCard {
    public CampfireGenerateCard() {
        super();
    }

    public Card createCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new CampfireCard(x, y);
    }
}
