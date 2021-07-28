package unsw.loopmania.gameModes;

import unsw.loopmania.GameMode;
import unsw.loopmania.GenerateItem;

public class SurvivalMode implements GameMode {
    public SurvivalMode() {

    }
    
    public boolean limitPurchase(Character character, GenerateItem item) {
        return false;
    }
}
