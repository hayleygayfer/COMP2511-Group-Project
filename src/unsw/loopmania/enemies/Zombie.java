package unsw.loopmania.enemies;

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

    
}
