package unsw.loopmania.enemies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.GenerateCard;
import unsw.loopmania.generateCards.TowerGenerateCard;
import unsw.loopmania.generateItems.TheOneRingGenerateItem;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;
import unsw.loopmania.PathPosition;
import unsw.loopmania.generateItems.StaffGenerateItem;
import unsw.loopmania.EnemyPositionObserver;

import org.javatuples.Pair;

public class Zombie extends BasicEnemy {

    //TODO: adjust the speed that zombies move
    private double movementSpeed;
    private List<EnemyPositionObserver> observers = new ArrayList<EnemyPositionObserver>();

    public Zombie(PathPosition position) {
        super(position);
        setDamage(2);
        setBattleRadius(2);
        setHealth(10);
        List<Pair<GenerateItem, Double>> droppableItems = new ArrayList<Pair<GenerateItem, Double>>();
        droppableItems.add(new Pair<GenerateItem, Double>(new StaffGenerateItem(), 0.50));
        droppableItems.add(new Pair<GenerateItem, Double>(new HealthPotionGenerateItem(), 0.2));
        droppableItems.add(new Pair<GenerateItem, Double>(new TheOneRingGenerateItem(), 0.05));
        setDroppableItems(droppableItems);
        // card drops
        List<Pair<GenerateCard, Double>> droppableCards = new ArrayList<Pair<GenerateCard, Double>>();
        droppableCards.add(new Pair<GenerateCard, Double>(new TowerGenerateCard(), 0.50));
        setDroppableCards(droppableCards);
        // xp and gold
        setMaxGoldGained(4);
        setExperienceGained(10);
    }

    /**
     * Moves a basic enemy
     */
    @Override
    public void move() {
        super.move();
    }

    /**
     * Renders the image of the zombie.
     * @return Image
     */
    public Image render() {
        return new Image((new File("src/images/zombie.png")).toURI().toString());
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
     * @param observer The enemy position observer to detach
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
