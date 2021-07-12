package unsw.loopmania.enemies;

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
}
