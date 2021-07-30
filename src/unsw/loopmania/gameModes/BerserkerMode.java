package unsw.loopmania.gameModes;

import unsw.loopmania.GameMode;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.Character;
import unsw.loopmania.itemTypes.ShieldType;
import unsw.loopmania.itemTypes.ArmourType;
import unsw.loopmania.itemTypes.HelmetType;

import java.util.List;

public class BerserkerMode implements GameMode {
    public BerserkerMode() {

    }
    
    public boolean limitPurchase(Character character, GenerateItem item) {
        List<GenerateItem> alreadyPurchased = character.getPurchased();

        for (GenerateItem purchased : alreadyPurchased) {
            if (purchased instanceof ShieldType || purchased instanceof ArmourType || purchased instanceof HelmetType) {
                if (item instanceof ShieldType || item instanceof ArmourType || item instanceof HelmetType) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
