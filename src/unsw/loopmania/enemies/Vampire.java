package unsw.loopmania.enemies;

import java.io.File;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;

public class Vampire extends BasicEnemy {
    // TODO write vampire
    public Vampire(PathPosition position) {
        super(position);
        setDamage(1);
        setBattleRadius(1);
        setHealth(1);
    }

    /**
     * Renders the image of the vampire.
     */
    public Image render() {
        return new Image((new File("src/images/vampire.png")).toURI().toString());
    }
}
