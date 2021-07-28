package unsw.loopmania.gameModes;

import unsw.loopmania.GameMode;
import unsw.loopmania.GenerateItem;

public class BerserkerMode implements GameMode {
    public BerserkerMode() {

    }
    
    public boolean limitPurchase(Character character, GenerateItem item) {
        return false;
    }
}
