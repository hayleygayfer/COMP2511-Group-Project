package unsw.loopmania.enemies;

import java.io.File;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;

public class Zombie extends BasicEnemy {
    private double movementSpeed;
    // TODO write zombie
    public Zombie(PathPosition position) {
        super(position);
        setDamage(1);
        setBattleRadius(1);
        setHealth(1);
    }

    @Override
    public void move(){
        super.move();
    }

    /**
     * Renders the image of the zombie.
     */
    public Image render() {
        return new Image((new File("src/images/slug.png")).toURI().toString());
    }
}
