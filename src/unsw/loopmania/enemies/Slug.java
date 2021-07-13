package unsw.loopmania.enemies;

import java.io.File;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;

public class Slug extends BasicEnemy {
    // TODO write slug
    public Slug(PathPosition position) {
        super(position);
        setDamage(3);
        setBattleRadius(1);
        setHealth(10);
        setSupportRadius(1);
    }

    /**
     * Renders the image of the slug.
     */
    public Image render() {
        return new Image((new File("src/images/slug.png")).toURI().toString());
    }

}
