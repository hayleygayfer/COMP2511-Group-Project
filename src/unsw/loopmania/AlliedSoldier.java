package unsw.loopmania;


import javafx.beans.property.SimpleIntegerProperty;

public class AlliedSoldier {
    private SimpleIntegerProperty health;
    private SimpleIntegerProperty baseDamage;
    private SimpleIntegerProperty baseDefence;

    public AlliedSoldier() {

    }

    public int getHealth() {
        return health.get();
    }

    public void loseHealth(double damage) {
        this.health.subtract(damage);
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public int getDamage() {
        return baseDamage.get();
    }
}
