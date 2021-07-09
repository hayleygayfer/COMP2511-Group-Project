package unsw.loopmania.enemies;

import unsw.loopmania.BasicEnemy;
import unsw.loopmania.PathPosition;

public class Slug extends BasicEnemy {
    // TODO write slug
    public Slug(PathPosition position) {
        super(position);
        setDamage(1);
        setBattleRadius(1);
        setHealth(1);
    }
}
