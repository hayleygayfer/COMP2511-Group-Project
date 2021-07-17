package unsw.loopmania.generateCards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.Card;
import unsw.loopmania.cards.VillageCard;

public class VillageGenerateCard extends GenerateCard {
    public VillageGenerateCard() {
        super();
    }

    public Card createCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new VillageCard(x, y);
    }
}
