package unsw.loopmania.enemies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.EnemyPositionObserver;
import unsw.loopmania.PathPosition;

public class Slug extends BasicEnemy {

    private List<EnemyPositionObserver> observers = new ArrayList<EnemyPositionObserver>();

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

    /**
     * Attaches an enemy position observer
     * @param observer The observer to attach
     */
    public void attach(EnemyPositionObserver observer) {
        observers.add(observer);
    }

    /**
     * Detaches an emepy position observer
     */
    public void detach(EnemyPositionObserver observer) {
        observers.remove(observer);
    }

    /**
     * Updates all enemy position observers
     */
    public void updateObservers() {
        for (EnemyPositionObserver observer : observers) {
            observer.encounter(this);
        }
    }

}
