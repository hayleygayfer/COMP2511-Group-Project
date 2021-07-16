package unsw.loopmania.items;

import unsw.loopmania.EquippableItem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;
public class Shield extends EquippableItem {
    private double reducedEnemyCriticalChance;

    // TODO write shield
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        reducedEnemyCriticalChance = 0.40;
    }

    /**
     * Chances of critical vampire attacks are reduced by 60%
     */
    @Override
    public double getModifiedCriticalChance(double baseCriticalChance) {
        return baseCriticalChance * reducedEnemyCriticalChance;
    }

    @Override
    public Image getImage() {
        return new Image((new File("src/images/shield.png")).toURI().toString());
    }
}
